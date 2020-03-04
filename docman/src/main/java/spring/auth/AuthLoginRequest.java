package spring.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthLoginRequest {

    String username;
    String password;
}
