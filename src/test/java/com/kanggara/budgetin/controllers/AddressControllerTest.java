package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.entities.AddressEntity;
import com.kanggara.budgetin.entities.ContactEntity;
import com.kanggara.budgetin.models.AddressResponse;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.CreateAddressRequest;
import com.kanggara.budgetin.models.UpdateAddressRequest;
import com.kanggara.budgetin.repository.AddressRepository;
import com.kanggara.budgetin.repository.ContactRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private AddressRepository addressRepository;

  @BeforeEach
  void setUp() {
    addressRepository.deleteAll();
    contactRepository.deleteAll();
    userRepository.deleteAll();

    UserEntity user = new UserEntity();
    user.setName("coba");
    user.setUsername("test");
    user.setPassword("rahasianya");
    user.setToken("token");
    user.setTokenExpiriedAt(System.currentTimeMillis() + 100000);
    userRepository.save(user);

    ContactEntity contactEntity = new ContactEntity();
    contactEntity.setId("test");
    contactEntity.setUser(user);
    contactEntity.setFirstName("coba");
    contactEntity.setLastName("test");
    contactEntity.setEmail("coba@test.com");
    contactEntity.setPhone("1122334455");
    contactRepository.save(contactEntity);
  }

  @Test
  void createAddressBadRequest() throws Exception {
    CreateAddressRequest req = new CreateAddressRequest();
    req.setCountry("");

    mockMvc.perform(
        post("/api/contacts/test/addresses")
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
  void createAddressSucess() throws Exception {
    CreateAddressRequest req = new CreateAddressRequest();
    req.setStreet("Jalan Jalan");
    req.setCity("Pekanbaru");
    req.setProvince("Riau");
    req.setPostalCode("28213");
    req.setCountry("Indonesia");

    mockMvc.perform(
        post("/api/contacts/test/addresses")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(req)))
        .andDo(result -> {
          WebResponse<AddressResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertNull(response.getError());
          assertNotNull(response.getData());
          assertEquals(req.getCity(), response.getData().getCity());
          assertEquals(req.getStreet(), response.getData().getStreet());
          assertEquals(req.getCountry(), response.getData().getCountry());
          assertEquals(req.getProvince(), response.getData().getProvince());
          assertEquals(req.getPostalCode(), response.getData().getPostalCode());

          AddressEntity db = addressRepository.findById(response.getData().getId()).orElseThrow();
          assertEquals(db.getCity(), response.getData().getCity());
          assertEquals(db.getStreet(), response.getData().getStreet());
          assertEquals(db.getCountry(), response.getData().getCountry());
          assertEquals(db.getProvince(), response.getData().getProvince());
          assertEquals(db.getPostalCode(), response.getData().getPostalCode());

          assertTrue(addressRepository.existsById(response.getData().getId()));
        });

  }

  @Test
  void getAddressNotFound() throws Exception {
    mockMvc.perform(
        get("/api/contacts/test/addresses/test")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound()).andDo(result -> {
          WebResponse<String> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<WebResponse<String>>() {
              });

          assertNotNull(response.getError());
        });
  }

  @Test
  void getAddressSucess() throws Exception {
    ContactEntity contactEntity = contactRepository.findById("test").orElseThrow();

    AddressEntity address = new AddressEntity();
    address.setId("test");
    address.setCity("Pekanbaru");
    address.setStreet("Jalan Jalan");
    address.setContact(contactEntity);
    address.setCountry("Indonesia");
    address.setProvince("Riau");
    address.setPostalCode("28213");
    addressRepository.save(address);

    mockMvc.perform(
        get("/api/contacts/test/addresses/test")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(result -> {
          WebResponse<AddressResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertNull(response.getError());
          assertNotNull(response.getData());
          assertEquals(address.getCity(), response.getData().getCity());
          assertEquals(address.getStreet(), response.getData().getStreet());
          assertEquals(address.getCountry(), response.getData().getCountry());
          assertEquals(address.getProvince(), response.getData().getProvince());
          assertEquals(address.getPostalCode(), response.getData().getPostalCode());

          AddressEntity db = addressRepository.findById("test").orElseThrow();
          assertEquals(db.getCity(), response.getData().getCity());
          assertEquals(db.getStreet(), response.getData().getStreet());
          assertEquals(db.getCountry(), response.getData().getCountry());
          assertEquals(db.getProvince(), response.getData().getProvince());
          assertEquals(db.getPostalCode(), response.getData().getPostalCode());

          assertTrue(addressRepository.existsById(response.getData().getId()));
        });

  }

  @Test
  void updateAddressBadRequest() throws Exception {
    UpdateAddressRequest req = new UpdateAddressRequest();
    req.setCountry("");

    ContactEntity contactEntity = contactRepository.findById("test").orElseThrow();

    AddressEntity address = new AddressEntity();
    address.setId("test");
    address.setCity("Pekanbaru");
    address.setStreet("Jalan Jalan");
    address.setContact(contactEntity);
    address.setCountry("Indonesia");
    address.setProvince("Riau");
    address.setPostalCode("28213");
    addressRepository.save(address);

    mockMvc.perform(
        put("/api/contacts/test/addresses/test")
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
  void updateAddressSuccess() throws Exception {
    ContactEntity contactEntity = contactRepository.findById("test").orElseThrow();

    AddressEntity address = new AddressEntity();
    address.setId("test");
    address.setCity("Pekanbaru");
    address.setStreet("Jalan Jalan");
    address.setContact(contactEntity);
    address.setCountry("Indonesia");
    address.setProvince("Riau");
    address.setPostalCode("28213");
    addressRepository.save(address);

    UpdateAddressRequest req = new UpdateAddressRequest();
    req.setStreet("Jalan Baru");
    req.setCity("Jakarta");
    req.setProvince("DKI");
    req.setPostalCode("112233");
    req.setCountry("Indonesia");

    mockMvc.perform(
        put("/api/contacts/test/addresses/test")
            .header("X-API-TOKEN", "token")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(req)))
        .andDo(result -> {
          WebResponse<AddressResponse> response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });

          assertNull(response.getError());
          assertNotNull(response.getData());
          assertEquals(req.getCity(), response.getData().getCity());
          assertEquals(req.getStreet(), response.getData().getStreet());
          assertEquals(req.getCountry(), response.getData().getCountry());
          assertEquals(req.getProvince(), response.getData().getProvince());
          assertEquals(req.getPostalCode(), response.getData().getPostalCode());

          AddressEntity db = addressRepository.findById("test").orElseThrow();
          assertEquals(db.getCity(), response.getData().getCity());
          assertEquals(db.getStreet(), response.getData().getStreet());
          assertEquals(db.getCountry(), response.getData().getCountry());
          assertEquals(db.getProvince(), response.getData().getProvince());
          assertEquals(db.getPostalCode(), response.getData().getPostalCode());

          assertTrue(addressRepository.existsById(response.getData().getId()));

        });
  }

}
