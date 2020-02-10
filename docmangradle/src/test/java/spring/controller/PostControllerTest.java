package spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import spring.dto.PostDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    private static final long ID = 0;
    private static final String CONTENT = "TEST";
    private static final String TITLE = "TEST";
    private static final String CATEGORY = "TEST";
    private static final String USERNAME = "TEST";
    private static PostDto postDto = new PostDto();

    private static final String url = "/api/v1/posts";
    private static String json;

    @Autowired
    MockMvc mockMvc;

    @BeforeAll
    public static void init() throws JsonProcessingException {
        postDto.setContent(CONTENT);
        postDto.setTitle(TITLE);
        postDto.setCategory(CATEGORY);
        postDto.setUsername(USERNAME);

        ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(postDto);
    }

    @Ignore
    void createPost() throws Exception {
        perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Ignore
    void updatePost() throws Exception {
        perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }

    @Test
    void showAllPosts() throws Exception {
        perform(get(url));
    }

    @Ignore
    void getSinglePost() throws Exception {
        perform(get(url + "/" + ID));
    }

    @Ignore
    void deletePost() throws Exception {
        perform(delete(url + "/" + ID));
    }

    @Test
    void getTitlePosts() throws Exception {
        perform(put(url + "/title/" + TITLE));
    }

    @Test
    void getCategoryPosts() throws Exception {
        perform(put(url + "/category/" + CATEGORY));
    }

    @Test
    void getUsernamePosts() throws Exception {
        perform(put(url + "/username/" + CATEGORY));
    }

    @Test
    void getTitleUsernamePosts() throws Exception {
        perform(put(url + "/" + TITLE + "?" + USERNAME));
    }

    void perform(RequestBuilder requestBuilder) throws Exception {
        mockMvc.perform(requestBuilder)
                .andDo(print());
    }
}
