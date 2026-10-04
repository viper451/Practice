package com.example.newpackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class NewPackageController {

    private static final Logger log =
            LoggerFactory.getLogger(NewPackageController.class);

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/hello")
    public Map<String, String> getHello() {
       atomicInteger.incrementAndGet();
        log.info("GET /api/hello called for  {} times",atomicInteger);

        return Map.of("message", "Hello from GET endpoint");
    }

    @PostMapping("/hello")
    public Map<String, String> postHello() {
        atomicInteger.incrementAndGet();
        log.info("POST /api/hello called for  {} times" ,atomicInteger);

        return Map.of("message", "Hello from POST endpoint");
    }
}
