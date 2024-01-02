package com.Student.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
@CrossOrigin(origins = "http://localhost:8080")
@SecurityRequirement(name = "Keycloak")//by this access token is added in header of all api
public class TestController {
    @GetMapping()
    public String get() {
        return "Get Mapping";
    }

    @PostMapping("/scope")
    public String post() {
        return "Post Mapping";
    }
    @PutMapping("/scope")
    public String update() {
        return "Put Mapping";
    }
}
