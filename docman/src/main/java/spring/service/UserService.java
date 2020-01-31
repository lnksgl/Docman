package spring.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.UserDto;
import spring.exception.PostNotFoundException;
import spring.mapper.UserMapper;
import spring.model.User;
import spring.repository.UserRepository;

import static java.util.stream.Collectors.toList;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserDto> showAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    @Transactional
    public void deletePost(long id) {
       userRepository.delete(userRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id)));
    }

    @Transactional
    public UserDto mapFromUserToDto(User user) {
        return userMapper.userToUserDto(user);
    }
}
