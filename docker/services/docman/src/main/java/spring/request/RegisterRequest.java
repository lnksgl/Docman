package spring.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;

@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class
RegisterRequest {

    String username;
    String password;
    String email;
}
