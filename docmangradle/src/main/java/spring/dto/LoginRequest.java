package spring.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    String username;
    String password;
}
