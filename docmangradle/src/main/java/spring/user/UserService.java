package spring.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Cacheable
    public List<UserDto> showAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    @Transactional
    public void deleteUser(long id) {
       userRepository.delete(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("For id " + id)));
    }

    @Cacheable
    public UserDto readSingleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("For id " + id));
        return mapFromUserToDto(user);
    }

    public boolean checkUsername(String username, String email) {
        return showUsername(username).isEmpty() && showEmail(email).isEmpty();
    }

    @Cacheable
    public List<UserDto> showUsername(String username) {
        return usersStream(userRepository.findByUsername(username).stream().collect(Collectors.toList()));
    }

    @Cacheable
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
