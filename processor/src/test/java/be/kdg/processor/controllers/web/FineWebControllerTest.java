package be.kdg.processor.controllers.web;

import be.kdg.processor.dto.FineDTO;
import be.kdg.processor.model.Offense;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Optional.empty;
import static org.assertj.core.api.Java6Assertions.not;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FineWebControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateFine() throws Exception {
        FineDTO fineDTO = new FineDTO(new Offense(), 50);
        String postJson = objectMapper.writeValueAsString(fineDTO);

        mockMvc.perform(post("/fine/newfine.do")
                .param("fineDTO", postJson))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/finesum.html"))
                .andExpect(model().attribute("fineDTO",
                        hasProperty("amount")));
    }
}