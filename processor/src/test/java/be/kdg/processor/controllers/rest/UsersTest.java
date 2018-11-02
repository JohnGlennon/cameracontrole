package be.kdg.processor.controllers.rest;

import be.kdg.processor.security.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAdmin() throws Exception {
        UserDTO userDTO = new UserDTO("username", "password");
        String requestJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/createadmin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().string(containsString("username")));
    }

    @Test
    public void testReadAdmins() throws Exception {
        UserDTO userDTO = new UserDTO("username", "password");
        String requestJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/createadmin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson));

        mockMvc.perform(get("/api/readadmins"))
                .andDo(print())
                .andExpect(content().string(containsString("username")));
    }

    @Test
    public void testChangePassword() throws Exception {
        UserDTO userDTO = new UserDTO("username", "password");
        String requestJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/createadmin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson));

        UserDTO updatedUserDTO = new UserDTO("username", "pwd");
        String requestJsonUpdate = objectMapper.writeValueAsString(updatedUserDTO);

        mockMvc.perform(put("/api/changepassword")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJsonUpdate))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(content().string(containsString("pwd")));
    }

    @Test
    public void testDeleteAdmin() throws Exception {
        UserDTO userDTO = new UserDTO("username", "password");
        String requestJson = objectMapper.writeValueAsString(userDTO);

        mockMvc.perform(post("/api/createadmin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson));

        mockMvc.perform(delete("/api/deleteadmin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isGone())
                .andDo(print())
                .andExpect(content().string(containsString("username")));
    }
}