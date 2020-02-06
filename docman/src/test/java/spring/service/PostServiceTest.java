package spring.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.dto.PostDto;
import spring.model.Post;
import spring.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PostServiceTest {

    private static final String CONTENT = "TEST";
    private static final String TITLE = "TEST";
    private static final String CATEGORY = "TEST";
    private static final String USERNAME = "TEST";
    private static PostDto postDto = new PostDto();

    @Mock
    PostService postService;
    @Mock
    PostRepository postRepository;

    @BeforeAll
    public static void init() {
        postDto.setContent(CONTENT);
        postDto.setTitle(TITLE);
        postDto.setCategory(CATEGORY);
        postDto.setUsername(USERNAME);
    }
}