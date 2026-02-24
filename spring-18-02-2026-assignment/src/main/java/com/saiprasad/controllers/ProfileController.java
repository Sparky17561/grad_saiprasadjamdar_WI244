package com.saiprasad.controllers;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ProfileController {

    private final Environment env;

    @Value("${app.message}")
    private String message;

    public ProfileController(Environment env) {
        this.env = env;
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "Active Profile: " +
                Arrays.toString(env.getActiveProfiles()) +
                " | Message: " + message;
    }
}
