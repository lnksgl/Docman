package spring.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import spring.request.RegisterRequest;
import spring.service.AuthService;
import spring.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static RegisterRequest registerRequest = new RegisterRequest();

    @Mock
    Autowired autowired;
    MockMvc mockMvc;

    @BeforeAll
    public static void init() {
        registerRequest.setUsername(USERNAME);
        registerRequest.setEmail(EMAIL);
        registerRequest.setPassword(PASSWORD);
    }
}