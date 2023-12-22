package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.RegisterUserRequest;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

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
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
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
		user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));
		user.setUsername("test");
		userRepository.save(user);

		RegisterUserRequest registerUserRequest = new RegisterUserRequest();
		registerUserRequest.setName("Test OK");
		registerUserRequest.setPassword("secrets");
		registerUserRequest.setUsername("test");

		mockMvc.perform(
				post("/api/users")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
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
	void testRegisterBadRequest1() throws Exception {
		RegisterUserRequest registerUserRequest = new RegisterUserRequest();

		mockMvc.perform(
				post("/api/users")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
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
	void testRegisterBadRequest2() throws Exception {
		RegisterUserRequest registerUserRequest = new RegisterUserRequest();
		registerUserRequest.setUsername("test");
		registerUserRequest.setPassword("secrets");

		mockMvc.perform(
				post("/api/users")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
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
	void testRegisterBadRequest3() throws Exception {
		RegisterUserRequest registerUserRequest = new RegisterUserRequest();
		registerUserRequest.setName("test");
		registerUserRequest.setPassword("secrets");

		mockMvc.perform(
				post("/api/users")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
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
