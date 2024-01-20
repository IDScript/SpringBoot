package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.UpdateUserRequest;
import com.kanggara.budgetin.models.UserResponse;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.security.BCrypt;

@SpringBootTest
@AutoConfigureMockMvc
class UpdateUserTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserRepository userRepository;

  @Test
  void updateUserUnautorized() throws Exception {
    UpdateUserRequest updateUserRequest = new UpdateUserRequest();

    mockMvc.perform(
        patch("/api/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateUserRequest)))
        .andExpect(
            status().isUnauthorized())
        .andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void updateUserSuccess() throws Exception {

    UserEntity userEntity = new UserEntity();
    userEntity.setName("test");
    userEntity.setUsername("test");
    userEntity.setPassword(BCrypt.hashpw("passwordpanjang", BCrypt.gensalt()));
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() + 360000);
    userRepository.save(userEntity);

    UpdateUserRequest updateUserRequest = new UpdateUserRequest();
    updateUserRequest.setName("Nama Baru");
    updateUserRequest.setPassword("PasswordBaru");

    mockMvc.perform(
        patch("/api/user")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateUserRequest))
            .header("X-API-TOKEN", "token"))
        .andExpect(
            status().isOk())
        .andDo(result -> {
          WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals("Nama Baru", response.getData().getName());
          assertEquals("test", response.getData().getUsername());

          UserEntity userDb = userRepository.findById("test").orElse(null);

          assertNotNull(userDb);
          assertTrue(BCrypt.checkpw("PasswordBaru", userDb.getPassword()));
          assertFalse(BCrypt.checkpw("passwordpanjang", userDb.getPassword()));
        });
  }

}
