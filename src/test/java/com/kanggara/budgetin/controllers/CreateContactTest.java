package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.ContactResponse;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.CreateContactRequest;
import com.kanggara.budgetin.repository.ContactRepository;

@SpringBootTest
@AutoConfigureMockMvc
class CreateContactTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    contactRepository.deleteAll();
    userRepository.deleteAll();

    UserEntity user = new UserEntity();
    user.setName("coba");
    user.setUsername("test");
    user.setPassword("rahasianya");
    user.setToken("token");
    user.setTokenExpiriedAt(System.currentTimeMillis() + 100000);
    userRepository.save(user);
  }

  @Test
  void createContactBadRequest() throws Exception {
    CreateContactRequest req = new CreateContactRequest();
    req.setFirstName("");
    req.setLastName("");
    req.setEmail("");
    req.setPhone("");

    mockMvc.perform(
        post("/api/contact")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(req)))
        .andExpect(
            status().isBadRequest())
        .andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<WebResponse<String>>() {
              });

          assertNotNull(response.getError());
        });

  }

  @Test
  void createContactSucess() throws Exception {
    CreateContactRequest req = new CreateContactRequest();
    req.setFirstName("Nama");
    req.setLastName("Belakang");
    req.setEmail("coba@tes.abc");
    req.setPhone("621");

    mockMvc.perform(
        post("/api/contact")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(req)))
        .andDo(result -> {
          WebResponse<ContactResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertNull(response.getError());
          assertNotNull(response.getData());
          assertEquals("Nama", response.getData().getFirstName());
          assertEquals("Belakang", response.getData().getLastName());
          assertEquals("coba@tes.abc", response.getData().getEmail());
          assertEquals("621", response.getData().getPhone());

          assertTrue(contactRepository.existsById(response.getData().getId()));
        });

  }
}
