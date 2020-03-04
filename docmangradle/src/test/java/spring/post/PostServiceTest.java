package spring.post;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PostServiceTest {

    private static final long ID = 0;
    private static final String CONTENT = "TEST";
    private static final String TITLE = "TEST";
    private static final String CATEGORY = "TEST";
    private static final String USERNAME = "TEST";
    private static Post post = new Post();
    private static PostDto postDto = new PostDto();
    private static final List<Post> posts = new ArrayList<Post>();

    @Mock
    PostMapper postMapper;
    @InjectMocks
    PostService postService;
    @Mock
    PostRepository postRepository;

    @BeforeAll
    public static void init() {
        post.setId(ID);
        post.setContent(CONTENT);
        post.setTitle(TITLE);
        post.setCategory(CATEGORY);
        post.setUsername(USERNAME);

        postDto.setId(ID);
        postDto.setContent(CONTENT);
        postDto.setTitle(TITLE);
        postDto.setCategory(CATEGORY);
        postDto.setUsername(USERNAME);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        postDto = postMapper.postToPostDto(post);
    }

    @Test
    void showAllPosts() {
        when(postRepository.findAll()).thenReturn(posts);
        Assertions.assertEquals(postService.showAllPosts(), posts);
    }

    @Ignore
        //void
    void createPost() {
        postService.createPost(postDto);
    }

    @Ignore
        //void
    void updatePost() {
        postService.updatePost(postDto);
    }

    @Test
    void readSinglePost() {
        when(postRepository.findById(ID)).thenReturn(java.util.Optional.of(post));
        when(postService.mapFromPostToDto(post)).thenReturn(postDto);
        Assertions.assertEquals(postService.readSinglePost(ID), postDto);
    }

    @Ignore
        //void
    void deletePost() {
        postService.deletePost(ID);
    }

    @Test
    void showCategoryPosts() {
        when(postRepository.findByCategory(CATEGORY)).thenReturn(posts);
        Assertions.assertEquals(postService.showCategoryPosts(CATEGORY), posts);
    }

    @Test
    void showTitlePost() {
        when(postRepository.findByTitle(TITLE)).thenReturn(posts);
        Assertions.assertEquals(postService.showTitlePost(TITLE), posts);
    }

    @Test
    void showTitleUsernamePosts() {
        when(postRepository.findByTitleAndUsername(TITLE, USERNAME)).thenReturn(posts);
        Assertions.assertEquals(postService.showTitleUsernamePosts(TITLE, USERNAME), posts);
    }

    @Test
    void showUsernamePosts() {
        when(postRepository.findByUsername(USERNAME)).thenReturn(posts);
        Assertions.assertEquals(postService.showUsernamePosts(USERNAME), posts);
    }

    @Test
    void mapFromPostToDto() {
        when(postMapper.postToPostDto(post)).thenReturn(postDto);
        Assertions.assertEquals(postService.mapFromPostToDto(post), postDto);
    }

    @Ignore
        //auth
    void mapFromDtoToPost() {
        when(postMapper.dtoToPost(postDto)).thenReturn(post);
        System.out.println(postService.mapFromDtoToPost(postDto));
    }
}
