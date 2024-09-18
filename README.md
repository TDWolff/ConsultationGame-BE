# mansion-backend
 
API Template File:
```java
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
```
This is a simple hello world script where you run a get to the backend running on localhost:8085/api/mansion/hello and it will return "Hello, World!".