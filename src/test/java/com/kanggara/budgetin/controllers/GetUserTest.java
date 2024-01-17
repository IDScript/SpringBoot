package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.models.UserResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kanggara.budgetin.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class GetUserTest {

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
  void getUserUnautorized() throws Exception {
    mockMvc.perform(
        get("/api/user")
            .accept(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "null"))
        .andExpectAll(status().isUnauthorized()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void getUserNoToken() throws Exception {
    mockMvc.perform(
        get("/api/user")
            .accept(MediaType.APPLICATION_JSON))
        .andExpectAll(status().isUnauthorized()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void getUserSuccess() throws Exception {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("test");
    userEntity.setUsername("test");
    userEntity.setPassword("passwordpanjang");
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() + 3600);
    userRepository.save(userEntity);

    mockMvc.perform(
        get("/api/user")
            .accept(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<UserResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
              assertNull(response.getError());
              assertNotNull(response.getData());
        });
  }

  @Test
  void getUserTokenExpiried() throws Exception {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("test");
    userEntity.setUsername("test");
    userEntity.setPassword("passwordpanjang");
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() - 3600);
    userRepository.save(userEntity);


    mockMvc.perform(
        get("/api/user")
            .accept(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isUnauthorized()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }
}
