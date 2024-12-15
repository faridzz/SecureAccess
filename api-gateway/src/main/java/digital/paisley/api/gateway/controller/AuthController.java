package digital.paisley.api.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/public/test")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public endpoint");

    }

    @GetMapping("/secured/test")
    public ResponseEntity<String> securedEndpoint() {
        return ResponseEntity.ok("Secured endpoint");
    }

}