package spring.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import spring.auth.AuthRegisterRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    private static final String USERNAME = "TEST";
    private static final String EMAIL = "TEST";
    private static final String PASSWORD = "TEST";
    private static AuthRegisterRequest authRegisterRequest = new AuthRegisterRequest();

    private static final String url = "/api/v1";
    private static String json;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init() throws JsonProcessingException {
        authRegisterRequest.setUsername(USERNAME);
        authRegisterRequest.setEmail(EMAIL);
        authRegisterRequest.setPassword(PASSWORD);

        ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        json = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(authRegisterRequest);
    }

    @Test
    void signUp() throws Exception {
        mockMvc.perform(
                post(url + "/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(
                post(url + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print());
    }
}
