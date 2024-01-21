package com.kanggara.budgetin.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController extends UnsupportedOperationException {
  @GetMapping("favicon.ico")
  void returnNoFavicon() {
  }

  @GetMapping("/favicon")
  void returnNoFavicon2() {
  }
}
