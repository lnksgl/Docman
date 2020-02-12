package spring.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.PostDto;
import spring.dto.UserDto;
import spring.service.UserService;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> showAllUsers() {
        return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getSingleUser(@PathVariable Long id) {
        return new ResponseEntity(userService.readSingleUser(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<PostDto>> getUsername(@PathVariable String username) {
        return new ResponseEntity(userService.showUsername(username), HttpStatus.OK);
    }
}
