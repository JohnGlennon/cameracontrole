package be.kdg.processor.controllers.rest;

import be.kdg.processor.fine.Fine;
import be.kdg.processor.offense.Offense;
import be.kdg.processor.offense.OffenseType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FineRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testApproveFine() throws Exception {
        Offense offense = new Offense("1-ABC-123", LocalDateTime.now(), OffenseType.EMISSION);
        Fine fine = new Fine(offense, 50);

        String requestJson = objectMapper.writeValueAsString(fine);

        mockMvc.perform(put("/api/fines/approve")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isAccepted())
                .andDo(print())
                .andExpect(content().string(containsString("50")));
    }
}