package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.lang.Nullable;
import org.springframework.http.MediaType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.UserResponse;
import com.kanggara.budgetin.models.UpdateUserRequest;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.RegisterUserRequest;
import com.kanggara.budgetin.repository.ContactRepository;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    contactRepository.deleteAll();
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
    userEntity.setPassword(BCrypt.hashpw("passwordpanjang", BCrypt.gensalt()));
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() + 36000);
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
    userEntity.setPassword(BCrypt.hashpw("passwordpanjang", BCrypt.gensalt()));
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
          assertEquals("Token Expiried", response.getError());
        });
  }

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

  @Test
  void updateUserSuccess2() throws Exception {

    UserEntity userEntity = new UserEntity();
    userEntity.setName("test");
    userEntity.setUsername("test");
    userEntity.setPassword(BCrypt.hashpw("passwordpanjang", BCrypt.gensalt()));
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() + 360000);
    userRepository.save(userEntity);

    UpdateUserRequest updateUserRequest = new UpdateUserRequest();
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
          assertEquals("test", response.getData().getUsername());

          UserEntity userDb = userRepository.findById("test").orElse(null);

          assertNotNull(userDb);
          assertTrue(BCrypt.checkpw("PasswordBaru", userDb.getPassword()));
          assertFalse(BCrypt.checkpw("passwordpanjang", userDb.getPassword()));
        });
  }

  @Test
  void updateUserSuccess3() throws Exception {

    UserEntity userEntity = new UserEntity();
    userEntity.setName("test");
    userEntity.setUsername("test");
    userEntity.setPassword(BCrypt.hashpw("passwordpanjang", BCrypt.gensalt()));
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() + 360000);
    userRepository.save(userEntity);

    UpdateUserRequest updateUserRequest = new UpdateUserRequest();
    updateUserRequest.setName("Nama Baru");

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
        });
  }

  @Test
  void updateUserNull() throws Exception {
    UserEntity userEntity = new UserEntity();
    userEntity.setName("test");
    userEntity.setUsername("test");
    userEntity.setPassword(BCrypt.hashpw("passwordpanjang", BCrypt.gensalt()));
    userEntity.setToken("token");
    userEntity.setTokenExpiriedAt(System.currentTimeMillis() + 360000);
    userRepository.save(userEntity);

    UpdateUserRequest updateUserRequest = new UpdateUserRequest();

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

          UserEntity userDb = userRepository.findById("test").orElse(null);

          assertNotNull(userDb);
        });
  }
}
