package com.kanggara.budgetin.controllers;

import com.kanggara.budgetin.models.VersionResponse;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpWebController {

  final BuildProperties buildProperties;

  public HttpWebController(BuildProperties buildProperties) {
    this.buildProperties = buildProperties;
  }

  @GetMapping("/version")
  public VersionResponse coba(@RequestParam(required = false) String data) {
    String artifac = buildProperties.getArtifact();
    String version = buildProperties.getVersion();
    return VersionResponse.builder().data(data).version(version).artifac(artifac).build();
  }

  @GetMapping(value = { "/favicon", "/favicon.ico", "sitemap.xml" })
  public void returnNoFavicon() {
    // TODO: document why this method is empty
  }

}
