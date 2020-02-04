package spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.UserDto;
import spring.exception.PostNotFoundException;
import spring.mapper.UserMapper;
import spring.model.User;
import spring.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<UserDto> showAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    @Transactional
    public void deletePost(long id) {
       userRepository.delete(userRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
    }

    public UserDto readSingleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromUserToDto(user);
    }

    public boolean checkUsername(String username, String email) {
        if (showUsername(username).isEmpty() && showEmail(email).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List<UserDto> showUsername(String username) {
        return usersStream(userRepository.findByUsername(username).stream().collect(Collectors.toList()));
    }

    public List<User> showEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDto> usersStream(List<User> users) {
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    public UserDto mapFromUserToDto(User user) {
        return userMapper.userToUserDto(user);
    }

    public User mapFromDtoToUser(UserDto user) {
        return userMapper.dtoToUser(user);
    }
}
