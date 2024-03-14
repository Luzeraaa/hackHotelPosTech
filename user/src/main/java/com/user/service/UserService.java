package com.user.service;

import com.user.domain.SearchParams;
import com.user.domain.User;
import com.user.dto.UserDTO;
import com.user.repository.UserRepository;
import com.user.util.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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

    var userPagination = userRepository.findById(searchParams.getId(),pageRequest);

    return new Pagination<>(userPagination);
  }
}
