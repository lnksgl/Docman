package spring.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import spring.request.RegisterRequest;

@SpringBootTest
@AutoConfigureMockMvc
class AuthServiceTest {

    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static RegisterRequest registerRequest = new RegisterRequest();

    @Mock
    AuthService authService;

    @BeforeAll
    public static void init() {
        registerRequest.setUsername(USERNAME);
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    void signUp() {
        Assertions.assertEquals(authService.signUp(registerRequest).getStatusCode(), HttpStatus.OK);
    }
}