package spring.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1")
public class AuthController {

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
