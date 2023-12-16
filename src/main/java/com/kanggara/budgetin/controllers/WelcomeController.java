package com.kanggara.budgetin.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class WelcomeController {
    @GetMapping()
    public String welcomeGet(@RequestParam(required = false) String id) {
        if (id == null || id.isEmpty() || id.isBlank() || id.length() < 1) {
            return "null";
        }
        return "id: " + id;
    }
}
