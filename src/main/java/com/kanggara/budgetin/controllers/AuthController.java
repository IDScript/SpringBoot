package com.kanggara.budgetin.controllers;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.TokenResponse;
import com.kanggara.budgetin.services.AuthService;
import com.kanggara.budgetin.models.LoginUserRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final AuthService authService;

  AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping(path = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest loginUserRequest) {
    TokenResponse tokenResponse = authService.login(loginUserRequest);
    return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
  }

  @DeleteMapping(path = "/api/logout", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> logout(UserEntity userEntity) {
    authService.logout(userEntity);
    return WebResponse.<String>builder().data("OK").build();
  }
}
