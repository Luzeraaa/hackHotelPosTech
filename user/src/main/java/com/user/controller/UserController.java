package com.user.controller;

import com.user.domain.SearchParams;
import com.user.domain.User;
import com.user.dto.UpdateUserDTO;
import com.user.dto.UserDTO;
import com.user.service.UserService;
import com.user.util.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping(path = "/user")
public record UserController(
        UserService userService
) {

  private static final String ID = "id";
  private static final String USER_PATH = "/user/%s";

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
    var user = userService.createUser(userDTO);
    return ResponseEntity.created(URI.create(format(USER_PATH, user.getId()))).body(user);
  }

  @GetMapping()
  public ResponseEntity<Pagination<User>> findUserByParam(@ModelAttribute SearchParams searchParams) {
    var user = userService.findUserByParam(searchParams);
    return ResponseEntity.ok(user);
  }

  @DeleteMapping( params = ID)
  public ResponseEntity<Void> deleteUser(Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(params = ID)
  public ResponseEntity<User> updateUserById(Long id , @RequestBody UpdateUserDTO updateUserDTO) {
    var user = userService.updateUserById(id, updateUserDTO);
    return ResponseEntity.ok(user);
  }

}
