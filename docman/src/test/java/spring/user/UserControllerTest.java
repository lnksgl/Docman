package spring.user;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final long ID = 0;
    private static final String USERNAME = "TEST";

    private static final String url = "/api/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showAllUsers() throws Exception {
        perform(get(url));
    }

    @Test
    void getUsername() throws Exception {
        perform(get(url + "/username/" + USERNAME));
    }

    @Ignore //write ID
    void getSingleUser() throws Exception {
        perform(get(url + "/" + ID));
    }

    @Ignore //write ID
    void deleteUser() throws Exception {
        perform(delete(url + "/" + ID));
    }

    void perform(RequestBuilder requestBuilder) throws Exception {
        mockMvc.perform(requestBuilder)
                .andDo(print());
    }
}
