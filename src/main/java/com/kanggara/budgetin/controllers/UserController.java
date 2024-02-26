package com.kanggara.budgetin.controllers;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.UserResponse;
import com.kanggara.budgetin.services.UserService;
import com.kanggara.budgetin.models.UpdateUserRequest;
import com.kanggara.budgetin.models.RegisterUserRequest;

import org.springframework.lang.NonNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(path = "/api/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> register(@RequestBody RegisterUserRequest registerUserRequest) {
    userService.register(registerUserRequest);
    return WebResponse.<String>builder().data("OK").build();
  }

  @GetMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<UserResponse> get(UserEntity userEntity) {
    UserResponse userResponse = userService.get(userEntity);
    return WebResponse.<UserResponse>builder().data(userResponse).build();
  }

  @PatchMapping(path = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<UserResponse> update(
      @NonNull UserEntity userEntity,
      @NonNull @RequestBody UpdateUserRequest updateUserRequest) {
    UserResponse userResponse = userService.update(userEntity, updateUserRequest);
    return WebResponse.<UserResponse>builder().data(userResponse).build();
  }
}
