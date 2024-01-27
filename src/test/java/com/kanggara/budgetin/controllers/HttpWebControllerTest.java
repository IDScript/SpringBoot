package com.kanggara.budgetin.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HttpWebControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest
  @CsvSource({ "/favicon", "/favicon.ico" })
  void noFavicon(String url) throws Exception {
    mockMvc.perform(get(url)).andExpectAll(status().isOk());
  }

  @ParameterizedTest
  @CsvSource({ "data", "coba", "coba1", "coba2", "coba3", "coba4", "coba5" })
  void appVersion(String data) throws Exception {
    mockMvc.perform(get("/test?data=" + data)).andExpectAll(status().isOk());
  }
}
