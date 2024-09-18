package com.nighthawk.spring_portfolio.hacks.mansion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/mansion")
public class MansionApiController {
    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello, World!");
    }
}
