package spring.controller;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring.dto.PostDto;
import spring.service.PostService;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class PostControllerTest {

    private static final String CONTENT = "TEST";
    private static final String TITLE = "TEST";
    private static final String CATEGORY = "TEST";
    private static final String USERNAME = "TEST";
    private static PostDto postDto = new PostDto();

    @Mock
    PostService postService;
    MockMvc mockMvc;

    @BeforeAll
    public static void init() {
        postDto.setContent(CONTENT);
        postDto.setTitle(TITLE);
        postDto.setCategory(CATEGORY);
        postDto.setUsername(USERNAME);
    }
}