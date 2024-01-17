package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.TokenResponse;
import com.kanggara.budgetin.models.LoginUserRequest;
import com.kanggara.budgetin.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @Test
  void loginFailUserNotFound() throws Exception {
    LoginUserRequest loginUserRequest = new LoginUserRequest();
    loginUserRequest.setUsername("test");
    loginUserRequest.setPassword("rahasiaya");
    mockMvc.perform(
        post("/api/login")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginUserRequest)))
        .andExpectAll(
            status().isUnauthorized())
        .andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });

  }

  @Test
  void loginFailWrongPass() throws Exception {
    UserEntity user = new UserEntity();
    user.setName("Test NOK");
    user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
    user.setUsername("test");
    userRepository.save(user);

    LoginUserRequest loginUserRequest = new LoginUserRequest();
    loginUserRequest.setUsername("test");
    loginUserRequest.setPassword("salahya");
    mockMvc.perform(
        post("/api/login")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginUserRequest)))
        .andExpectAll(status().isUnauthorized()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void loginFailWrongUser() throws Exception {
    UserEntity user = new UserEntity();
    user.setName("Test NOK");
    user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
    user.setUsername("test");
    userRepository.save(user);

    LoginUserRequest loginUserRequest = new LoginUserRequest();
    loginUserRequest.setUsername("testya");
    loginUserRequest.setPassword("secrets");
    mockMvc.perform(
        post("/api/login")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginUserRequest)))
        .andExpectAll(status().isUnauthorized()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void loginFailNullUser() throws Exception {
    UserEntity user = new UserEntity();
    user.setName("Test NOK");
    user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
    user.setUsername("test");
    userRepository.save(user);

    LoginUserRequest loginUserRequest = new LoginUserRequest();
    loginUserRequest.setUsername(null);
    loginUserRequest.setPassword("secrets");
    mockMvc.perform(
        post("/api/login")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginUserRequest)))
        .andExpectAll(status().isBadRequest()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void loginSuccess() throws Exception {
    UserEntity user = new UserEntity();
    user.setName("Test OK");
    user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
    user.setUsername("test");
    userRepository.save(user);

    LoginUserRequest loginUserRequest = new LoginUserRequest();
    loginUserRequest.setUsername("test");
    loginUserRequest.setPassword("secrets");
    mockMvc.perform(
        post("/api/login")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginUserRequest)))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<TokenResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertNotNull(response.getData().getToken());
          assertNotNull(response.getData().getExpiredAt());

          UserEntity userEntity = userRepository.findById("test").orElse(null);
          assertNotNull(userEntity);
          assertEquals(userEntity.getToken(), response.getData().getToken());
          assertEquals(userEntity.getTokenExpiriedAt(), response.getData().getExpiredAt());
        });

  }
}
