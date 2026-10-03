package com.example.newpackage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class NewPackageController {

    @GetMapping("/hello")
    public Map<String, String> getHello() {
        return Map.of("message", "Hello from GET endpoint");
    }

    @PostMapping("/hello")
    public Map<String, String> postHello() {
        return Map.of("message", "Hello from POST endpoint");
    }
}
