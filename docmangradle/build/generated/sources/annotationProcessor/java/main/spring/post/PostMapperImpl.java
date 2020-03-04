package spring.post;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-04T15:23:59+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 9 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto postToPostDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        postDto.setId( post.getId() );
        postDto.setContent( post.getContent() );
        postDto.setTitle( post.getTitle() );
        postDto.setCategory( post.getCategory() );
        postDto.setUsername( post.getUsername() );

        return postDto;
    }

    @Override
    public Post dtoToPost(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postDto.getId() );
        post.setTitle( postDto.getTitle() );
        post.setContent( postDto.getContent() );
        post.setUsername( postDto.getUsername() );
        post.setCategory( postDto.getCategory() );

        return post;
    }
}
