package spring.post;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto postToPostDto(Post post);

    Post dtoToPost(PostDto postDto);
}
