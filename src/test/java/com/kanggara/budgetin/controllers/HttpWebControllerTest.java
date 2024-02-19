package com.kanggara.budgetin.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.kanggara.budgetin.models.VersionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HttpWebControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @ParameterizedTest
  @CsvSource({ "/favicon", "/favicon.ico" })
  void noFavicon(String url) throws Exception {
    mockMvc.perform(get(url)).andExpectAll(status().isOk());
  }

  @ParameterizedTest
  @CsvSource({ "data", "coba", "coba1", "coba2", "coba3", "coba4", "coba5" })
  void appVersion(String data) throws Exception {
    mockMvc.perform(get("/version?data=" + data))
        .andExpectAll(status().isOk()).andDo(result -> {
          VersionResponse response = objectMapper.readValue(
              result.getResponse().getContentAsString(),
              new TypeReference<>() {
              });
          assertNotNull(response.getArtifac());
          assertNotNull(response.getVersion());
          assertEquals(data, response.getData());

        });
  }
}
