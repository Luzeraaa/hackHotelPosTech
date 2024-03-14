package com.user.controller;

import com.user.domain.SearchParams;
import com.user.domain.User;
import com.user.dto.UserDTO;
import com.user.service.UserService;
import com.user.util.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public record UserController(
        UserService userService
){

  private static final String ID = "id";

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
    var user = userService.createUser(userDTO);
    return ResponseEntity.ok(user);
  }

  @GetMapping()
  public ResponseEntity<Pagination<User>> findUserByParam(@ModelAttribute SearchParams searchParams) {
    var user = userService.findUserByParam(searchParams);
    return ResponseEntity.ok(user);
  }

}
