package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.RegisterUserRequest;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegisterTest {

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
  void testRegisterSuccess() throws Exception {
    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
    registerUserRequest.setName("Test OK");
    registerUserRequest.setPassword("secrets");
    registerUserRequest.setUsername("test");

    mockMvc.perform(
        post("/api/users")
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registerUserRequest)))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertEquals("OK", response.getData());
        });
  }

  @Test
  void testRegisterDuplicate() throws Exception {
    UserEntity user = new UserEntity();
    user.setName("Test OK");
    user.setUsername("test");
    user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
    userRepository.save(user);

    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
    registerUserRequest.setName("Test OK");
    registerUserRequest.setUsername("test");
    registerUserRequest.setPassword("secrets");

    mockMvc.perform(
        post("/api/users")
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registerUserRequest)))
        .andExpectAll(status().isBadRequest()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertNotNull(response.getError());
        });
  }

  @ParameterizedTest
  @CsvSource({
      ",,",
      ",,secrets",
      ",test,",
      ",test,secrets",
      "nama,,",
      "nama,test,",
      "nama,,secrets",
  })
  void testRegisterBadRequest(
      @Nullable String username,
      @Nullable String name,
      @Nullable String pass) throws Exception {
    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
    registerUserRequest.setUsername(username);
    registerUserRequest.setPassword(pass);
    registerUserRequest.setName(name);

    mockMvc.perform(
        post("/api/users")
            .accept(APPLICATION_JSON)
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(registerUserRequest)))
        .andExpectAll(status().isBadRequest()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertNotNull(response.getError());
        });
  }
}
