package com.thetechmaddy.authservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalIdentityController {


    @GetMapping(value = "/auth/home")
    public String home() {
        return "Hello";
    }

}
