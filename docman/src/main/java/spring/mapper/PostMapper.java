package spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.dto.PostDto;
import spring.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDto postToPostDto(Post post);
    Post dtoToPost(PostDto postDto);
}
