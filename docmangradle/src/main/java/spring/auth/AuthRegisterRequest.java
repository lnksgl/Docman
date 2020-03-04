package spring.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class
AuthRegisterRequest {

    String username;
    String password;
    String email;
}
