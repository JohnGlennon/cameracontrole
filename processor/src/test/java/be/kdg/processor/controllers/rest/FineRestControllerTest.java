package be.kdg.processor.controllers.rest;

import be.kdg.processor.fine.FineDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FineRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateFine() throws Exception {
        Offense offense = new Offense("1-ABC-123", LocalDateTime.now(), OffenseType.EMISSION);

        FineDTO fineDTO = new FineDTO(offense, 50);
        String requestJson = objectMapper.writeValueAsString(fineDTO);

        mockMvc.perform(get(String.format("/api/fines/between/%s/%s", LocalDateTime.now().minusMinutes(5), LocalDateTime.now().plusMinutes(5)))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(content().string(containsString("1-ABC-123")));
    }
}