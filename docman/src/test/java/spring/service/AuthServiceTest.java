package spring.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import spring.request.LoginRequest;
import spring.request.RegisterRequest;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AuthServiceTest {

    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static RegisterRequest registerRequest = new RegisterRequest();
    private static LoginRequest loginRequest = new LoginRequest();

    @Mock
    AuthService authService;

    @BeforeAll
    public static void init() {
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

    @Ignore
    void signUp() {
        Assertions.assertEquals(authService.signUp(registerRequest).getStatusCode(), HttpStatus.OK);
    }

    @Ignore
    void login() {
        System.out.println(authService.login(loginRequest).getUsername());
    }
}
