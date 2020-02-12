package spring.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {

    Long id;
    String content;
    String title;
    String category;
    String username;
}
