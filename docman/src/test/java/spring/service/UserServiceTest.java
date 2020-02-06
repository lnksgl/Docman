package spring.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.model.User;
import spring.repository.PostRepository;
import spring.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UserServiceTest {

    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static User user = new User();

    @Mock
    UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeAll
    public static void init() {
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
    }
}