package com.kanggara.budgetin.controllers;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.UserResponse;
import com.kanggara.budgetin.services.UserService;
import com.kanggara.budgetin.models.RegisterUserRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> register(@RequestBody RegisterUserRequest registerUserRequest) {
    userService.register(registerUserRequest);
    return WebResponse.<String>builder().data("OK").build();
  }

  @GetMapping(path = "/api/user", produces= MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<UserResponse> get(UserEntity userEntity){
    UserResponse userResponse = userService.get(userEntity);
    return WebResponse.<UserResponse>builder().data(userResponse).build();

  }
}
