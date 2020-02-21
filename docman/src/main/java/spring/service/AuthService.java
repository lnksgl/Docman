package spring.service;

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
import spring.request.LoginRequest;
import spring.request.RegisterRequest;
import spring.model.User;
import spring.repository.UserRepository;
import spring.response.AuthenticationResponse;
import spring.security.JwtProvider;

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

    public ResponseEntity signUp(RegisterRequest registerRequest) {
        Span span = tracer.buildSpan("docman").start();

        if (userService.checkUsername(registerRequest.getUsername(), registerRequest.getEmail())) {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(encodePassword(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
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
    public AuthenticationResponse login(LoginRequest loginRequest) {
        Span span = tracer.buildSpan("docman").start();

        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken();

        span.setTag("https.status_code", 200);
        span.finish();

        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
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
