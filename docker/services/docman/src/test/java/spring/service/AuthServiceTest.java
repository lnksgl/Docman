package spring.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.model.User;
import spring.request.LoginRequest;
import spring.request.RegisterRequest;
import spring.security.JwtProvider;

import javax.imageio.plugins.tiff.TIFFTag;
import javax.persistence.TableGenerator;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AuthServiceTest {

    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static User user = new User();
    private static RegisterRequest registerRequest = new RegisterRequest();
    private static LoginRequest loginRequest = new LoginRequest();

    @InjectMocks
    AuthService authService;
    @Mock
    UserService userService;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;

    @BeforeAll
    public static void init() {
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        registerRequest.setUsername(USERNAME);
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);

        loginRequest.setUsername(USERNAME);
        loginRequest.setPassword(PASSWORD);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void signUp() {
        when(userService.checkUsername(USERNAME, EMAIL)).thenReturn(true);
        when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD);
        Assertions.assertEquals(authService.signUp(registerRequest).getStatusCode(), HttpStatus.OK);
    }

    @Ignore //return Authentication
    void login() {
//        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD)))
//            .thenReturn(new Authentication());
        System.out.println(authService.login(loginRequest).getUsername());
    }
}
