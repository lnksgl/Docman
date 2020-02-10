package spring.service;

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
import spring.dto.UserDto;
import spring.mapper.UserMapper;
import spring.model.User;
import spring.repository.PostRepository;
import spring.repository.UserRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class UserServiceTest {

    private static long ID = 0;
    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static User user = new User();

    @Autowired
    UserMapper userMapper;
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeAll
    public static void init() {
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createPost() {

        userService.createUser(user);
    }

    @Test
    void showAllUsers() {
        userService.showAllUsers();
    }

    @Test
    void deleteUser() {
        userService.deleteUser(ID);
    }

    @Ignore
    void readSingleUser() {
        when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(user));
        System.out.println(userService.readSingleUser(ID));
    }

    @Test
    void checkUsername() {
        Assertions.assertFalse(userService.checkUsername(USERNAME, PASSWORD));
    }

    @Ignore
    void showUsername() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(java.util.Optional.ofNullable(user));
        System.out.println(userService.showUsername(USERNAME));
    }

    @Ignore
    void showEmail() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(Collections.singletonList(user));
        System.out.println(userService.showEmail(EMAIL));
    }
}
