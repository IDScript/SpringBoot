package com.kanggara.budgetin.controllers;

import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kanggara.budgetin.models.WebResponse;

@RestController
public class CobaController {

  final BuildProperties buildProperties;

  CobaController(BuildProperties buildProperties) {
    this.buildProperties = buildProperties;
  }

  @GetMapping("/test")
  public WebResponse<String> coba(@RequestParam(required = false) String data) {
    String artifac = buildProperties.getArtifact();
    String version = buildProperties.getVersion();
    return WebResponse.<String>builder().data(data + " " + artifac + " " + version).build();
  }
}
