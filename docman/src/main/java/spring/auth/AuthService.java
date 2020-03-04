package spring.auth;

import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.user.UserService;
import spring.user.User;
import spring.jwt.JwtProvider;

import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CacheConfig(cacheNames = {"service"})
public class AuthService {

    private static final Logger LOG = Logger.getLogger(AuthService.class.getName());

    UserService userService;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtProvider jwtProvider;
    Tracer tracer;

    public ResponseEntity signUp(AuthRegisterRequest authRegisterRequest) {
        Span span = tracer.buildSpan("docman").start();

        if (userService.checkUsername(authRegisterRequest.getUsername(), authRegisterRequest.getEmail())) {
            User user = new User();
            user.setUsername(authRegisterRequest.getUsername());
            user.setPassword(encodePassword(authRegisterRequest.getPassword()));
            user.setEmail(authRegisterRequest.getEmail());
            userService.createUser(user);

            LOG.log(Level.INFO,"signUp " + HttpStatus.OK);

            span.setTag("https.status_code", 200);
            span.finish();

            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOG.log(Level.INFO,"signUp " + HttpStatus.FORBIDDEN);

            span.setTag("https.status_code", 403);
            span.finish();

            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Cacheable
    public AuthResponse login(AuthLoginRequest authLoginRequest) {
        Span span = tracer.buildSpan("docman").start();

        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginRequest.getUsername(),
                authLoginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken();

        span.setTag("https.status_code", 200);
        span.finish();

        return new AuthResponse(authenticationToken, authLoginRequest.getUsername());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        return Optional.of(principal);
    }
}
