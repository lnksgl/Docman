package spring.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;

@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class
AuthRegisterRequest {

    String username;
    String password;
    String email;
}
