package com.user.service;

import com.user.domain.SearchParams;
import com.user.domain.User;
import com.user.dto.UpdateUserDTO;
import com.user.dto.UserDTO;
import com.user.exception.FailedDependencyException;
import com.user.exception.NotFoundException;
import com.user.repository.UserRepository;
import com.user.util.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;

import static com.user.util.Constant.USER_NOT_FOUND;
import static com.user.util.Validators.isNullOrEmptyOrBlank;

@Service
public class UserService {

  private static final String INACCESSIBLE_FIELDS = "user update has inaccessible fields";
  private static final String NON_MATCHING = "non-matching fields between UserDTO and User";

  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(UserDTO userDTO) {
    return userRepository.save(new User(userDTO));
  }

  public Pagination<User> findUserByParam(SearchParams searchParams) {
    var pageRequest = PageRequest.of(searchParams.getOffset(), searchParams.getLimit());

    if (!searchParams.hasParams()) {
      return new Pagination<>(userRepository.findAll(pageRequest));
    }

    var userPagination = userRepository.findById(searchParams.getId(), pageRequest);

    return new Pagination<>(userPagination);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public User updateUserById(Long id, UpdateUserDTO updateUserDTO) {
    var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    updateUserByUpdateUserDTO(updateUserDTO, user);
    return userRepository.save(user);
  }

  private void updateUserByUpdateUserDTO(final UpdateUserDTO updateUserDTO, User user) {
    Field[] fields = UpdateUserDTO.class.getDeclaredFields();

    for (Field field : fields) {
      field.setAccessible(true);
      Object value;
      try {
        value = field.get(updateUserDTO);
      } catch (IllegalAccessException ex) {
        throw new FailedDependencyException(INACCESSIBLE_FIELDS, ex);
      }

      if (!isNullOrEmptyOrBlank(value)) {
        Field correspondingField;

        try {
          correspondingField = User.class.getDeclaredField(field.getName());
          correspondingField.setAccessible(true);

          correspondingField.set(user, value);

        } catch (NoSuchFieldException ex) {
          throw new FailedDependencyException(NON_MATCHING, ex);

        } catch (IllegalAccessException ex) {
          throw new FailedDependencyException(INACCESSIBLE_FIELDS, ex);
        }
      }
    }
  }

}
