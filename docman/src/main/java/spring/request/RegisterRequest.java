package spring.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class
RegisterRequest {

    String username;
    String password;
    String email;
}
