package spring.auth;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody AuthRegisterRequest authRegisterRequest) {
        return authService.signUp(authRegisterRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest authLoginRequest) {
        return authService.login(authLoginRequest);
    }
}
