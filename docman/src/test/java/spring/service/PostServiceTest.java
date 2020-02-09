package spring.service;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.CanIgnoreReturnValue;
import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import spring.dto.PostDto;
import spring.mapper.PostMapper;
import spring.model.Post;
import spring.repository.PostRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
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
    private static PostDto postDto;

    @Autowired
    PostMapper postMapper;
    @Mock
    PostService postService;
    @Mock
    PostRepository postRepository;

    @BeforeAll
    public static void init() {
        post.setContent(CONTENT);
        post.setTitle(TITLE);
        post.setCategory(CATEGORY);
        post.setUsername(USERNAME);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        postDto = postMapper.postToPostDto(post);
    }

    @Ignore
    void showAllPosts() {
        System.out.println(postService.showAllPosts());
    }

    @Ignore
    void createPost() {
        postService.createPost(postDto);
    }

    @Ignore
    void updatePost() {
        postService.updatePost(postDto);
    }

    @Ignore
    void readSinglePost() {
        when(postRepository.findById(ID)).thenReturn(java.util.Optional.of(post));
        System.out.println(postService.readSinglePost(ID));
    }

    @Ignore
    void deletePost() {
        postService.deletePost(ID);
    }

    @Ignore
    void showCategoryPosts() {
        when(postRepository.findByCategory(CATEGORY)).thenReturn(Collections.singletonList(post));
        System.out.println(postService.showCategoryPosts(CATEGORY));
    }

    @Ignore
    void showTitlePost() {
        when(postRepository.findByTitle(TITLE)).thenReturn(Collections.singletonList(post));
        System.out.println(postService.showTitlePost(TITLE));
    }

    @Ignore
    void showTitleUsernamePosts() {
        when(postRepository.findByTitleAndUsername(TITLE, USERNAME)).thenReturn(Collections.singletonList(post));
        System.out.println(postService.showTitleUsernamePosts(TITLE, USERNAME));
    }

    @Ignore
    void showUsernamePosts() {
        when(postRepository.findByUsername(USERNAME)).thenReturn(Collections.singletonList(post));
        System.out.println(postService.showUsernamePosts(USERNAME));
    }
}
