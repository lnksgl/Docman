package spring.dto;

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
