package com.ml.forecasttest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping(value = "/status")
    public ResponseEntity<?> getStatus() {
        return ResponseEntity.ok()
                .body("Its Running!!");
    }


}
