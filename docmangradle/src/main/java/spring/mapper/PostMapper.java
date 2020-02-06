package spring.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import spring.dto.PostDto;
import spring.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto postToPostDto(Post post);

    Post dtoToPost(PostDto postDto);
}
