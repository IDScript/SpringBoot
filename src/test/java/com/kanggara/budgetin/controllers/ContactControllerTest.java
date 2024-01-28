package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;
import java.util.UUID;

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
import com.kanggara.budgetin.entities.ContactEntity;
import com.kanggara.budgetin.models.ContactResponse;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.CreateContactRequest;
import com.kanggara.budgetin.models.UpdateContactRequest;
import com.kanggara.budgetin.repository.ContactRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

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

  @Test
  void updateContactBadRequest() throws Exception {
    UpdateContactRequest req = new UpdateContactRequest();
    req.setFirstName("");
    req.setLastName("");
    req.setEmail("");
    req.setPhone("");

    mockMvc.perform(
        put("/api/contact/123")
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
  void updateContactSucess() throws Exception {
    UserEntity userEntity = userRepository.findById("test").orElseThrow();

    ContactEntity contactEntity = new ContactEntity();
    contactEntity.setId(UUID.randomUUID().toString());
    contactEntity.setUser(userEntity);
    contactEntity.setFirstName("coba");
    contactEntity.setLastName("test");
    contactEntity.setEmail("coba@test.com");
    contactEntity.setPhone("1122334455");
    contactRepository.save(contactEntity);

    CreateContactRequest req = new CreateContactRequest();
    req.setFirstName("Nama");
    req.setLastName("Belakang");
    req.setEmail("coba@tes.abc");
    req.setPhone("621");

    mockMvc.perform(
        put("/api/contact/" + contactEntity.getId())
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
          assertEquals(req.getFirstName(), response.getData().getFirstName());
          assertEquals(req.getLastName(), response.getData().getLastName());
          assertEquals(req.getEmail(), response.getData().getEmail());
          assertEquals(req.getPhone(), response.getData().getPhone());

          assertTrue(contactRepository.existsById(response.getData().getId()));
        });

  }

  @Test
  void deleteContactNotFound() throws Exception {
    mockMvc.perform(
        delete("/api/contact/123")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            status().isNotFound())
        .andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<WebResponse<String>>() {
              });

          assertNotNull(response.getError());
        });
  }

  @Test
  void deleteContactSuccess() throws Exception {
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
        delete("/api/contact/" + contactEntity.getId())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertNotNull(response.getData());

        });
  }

  @Test
  void searchNotFound() throws Exception {
    mockMvc.perform(
        get("/api/contact")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<List<ContactResponse>> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals(0, response.getData().size());
          assertEquals(0, response.getPaging().getTotalPage());
          assertEquals(0, response.getPaging().getCurrentPage());
          assertEquals(10, response.getPaging().getSize());

        });
  }

  @Test
  void searchSuccess() throws Exception {
    UserEntity userEntity = userRepository.findById("test").orElseThrow();

    for (int i = 0; i < 100; i++) {
      ContactEntity contactEntity = new ContactEntity();
      contactEntity.setId(UUID.randomUUID().toString());
      contactEntity.setUser(userEntity);
      contactEntity.setFirstName("coba" + i);
      contactEntity.setLastName("test" + i);
      contactEntity.setEmail("coba@test.com");
      contactEntity.setPhone("1122334455");
      contactRepository.save(contactEntity);
    }

    mockMvc.perform(
        get("/api/contact")
            .queryParam("name", "coba")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<List<ContactResponse>> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals(10, response.getData().size());
          assertEquals(10, response.getPaging().getTotalPage());
          assertEquals(0, response.getPaging().getCurrentPage());
          assertEquals(10, response.getPaging().getSize());
        });

    mockMvc.perform(
        get("/api/contact")
            .queryParam("name", "test")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<List<ContactResponse>> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals(10, response.getData().size());
          assertEquals(10, response.getPaging().getTotalPage());
          assertEquals(0, response.getPaging().getCurrentPage());
          assertEquals(10, response.getPaging().getSize());
        });

    mockMvc.perform(
        get("/api/contact")
            .queryParam("email", "test.com")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<List<ContactResponse>> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals(10, response.getData().size());
          assertEquals(10, response.getPaging().getTotalPage());
          assertEquals(0, response.getPaging().getCurrentPage());
          assertEquals(10, response.getPaging().getSize());
        });

    mockMvc.perform(
        get("/api/contact")
            .queryParam("phone", "12233")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<List<ContactResponse>> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals(10, response.getData().size());
          assertEquals(10, response.getPaging().getTotalPage());
          assertEquals(0, response.getPaging().getCurrentPage());
          assertEquals(10, response.getPaging().getSize());
        });

    mockMvc.perform(
        get("/api/contact")
            .queryParam("phone", "12233")
            .queryParam("page", "1000")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .header("X-API-TOKEN", "token"))
        .andExpectAll(status().isOk()).andDo(result -> {
          WebResponse<List<ContactResponse>> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNull(response.getError());
          assertEquals(0, response.getData().size());
          assertEquals(10, response.getPaging().getTotalPage());
          assertEquals(1000, response.getPaging().getCurrentPage());
          assertEquals(10, response.getPaging().getSize());
        });
  }
}
