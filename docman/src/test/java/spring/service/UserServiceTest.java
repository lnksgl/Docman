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
import spring.dto.PostDto;
import spring.dto.UserDto;
import spring.mapper.UserMapper;
import spring.model.Post;
import spring.model.User;
import spring.repository.PostRepository;
import spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private static UserDto userDto = new UserDto();
    private static final List<User> users = new ArrayList<User>();

    @Mock
    UserMapper userMapper;
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeAll
    public static void init() {
        user.setId(ID);
        user.setUsername(USERNAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        userDto.setId(ID);
        userDto.setUsername(USERNAME);
        userDto.setEmail(EMAIL);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Ignore //void
    void createPost() {
        userService.createUser(user);
    }

    @Test
    void showAllUsers() {
        when(userRepository.findAll()).thenReturn(users);
        Assertions.assertEquals(userService.showAllUsers(), users);
    }

    @Ignore //void
    void deleteUser() {
        userService.deleteUser(ID);
    }

    @Test
    void readSingleUser() {
        when(userRepository.findById(ID)).thenReturn(java.util.Optional.of(user));
        when(userService.mapFromUserToDto(user)).thenReturn(userDto);
        Assertions.assertEquals(userService.readSingleUser(ID), userDto);
    }

    @Ignore
    void checkUsername() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(java.util.Optional.of(user));
        when(userRepository.findByEmail(EMAIL)).thenReturn(users);
        Assertions.assertFalse(userService.checkUsername(USERNAME, PASSWORD));
    }

    @Ignore
    void showUsername() {
        when(userRepository.findByUsername(USERNAME)).thenReturn(java.util.Optional.of(user));
        Assertions.assertEquals(userService.showUsername(USERNAME), new ArrayList<UserDto>());
    }

    @Test
    void showEmail() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(users);
        Assertions.assertEquals(userService.showEmail(EMAIL), users);
    }
}
