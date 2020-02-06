package spring.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.request.LoginRequest;
import spring.request.RegisterRequest;
import spring.response.AuthenticationResponse;
import spring.service.AuthService;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        return authService.signup(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
