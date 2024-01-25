package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanggara.budgetin.models.ContactResponse;
import com.kanggara.budgetin.entities.ContactEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.repository.ContactRepository;

@SpringBootTest
@AutoConfigureMockMvc
class GetContactTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ContactRepository contactRepository;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
    contactRepository.deleteAll();

    UserEntity user = new UserEntity();
    user.setName("coba");
    user.setUsername("test");
    user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
    user.setToken("token");
    user.setTokenExpiriedAt(System.currentTimeMillis() + 100000);
    userRepository.save(user);
  }

  @Test
  void getContactNoToken() throws Exception {
    mockMvc.perform(
        get("/api/contact/1122334455")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(status().isUnauthorized()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void getContactNotFound() throws Exception {

    mockMvc.perform(
        get("/api/contact/1122334455")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isNotFound()).andDo(result -> {
          WebResponse<ContactResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getError());
        });
  }

  @Test
  void getContactSuccess() throws Exception {
    UserEntity userEntity = userRepository.findById("test").orElseThrow();

    ContactEntity contactEntity = new ContactEntity();
    contactEntity.setId(UUID.randomUUID().toString());
    contactEntity.setUser(userEntity);
    contactEntity.setFirstName("coba");
    contactEntity.setLastName("test");
    contactEntity.setEmail("coba@test.com");
    contactEntity.setPhone("1122334455");
    contactRepository.save(contactEntity);

    mockMvc.perform(
        get("/api/contact/" + contactEntity.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<ContactResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertNotNull(response.getData());
          assertEquals(contactEntity.getId(), response.getData().getId());
          assertEquals(contactEntity.getEmail(), response.getData().getEmail());
          assertEquals(contactEntity.getPhone(), response.getData().getPhone());
          assertEquals(contactEntity.getLastName(), response.getData().getLastName());
          assertEquals(contactEntity.getFirstName(), response.getData().getFirstName());

        });
  }

}
