package spring.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.request.LoginRequest;
import spring.request.RegisterRequest;
import spring.service.AuthService;
import spring.response.AuthenticationResponse;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody RegisterRequest registerRequest) {
        return authService.signUp(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
