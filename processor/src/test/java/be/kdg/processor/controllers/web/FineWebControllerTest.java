package be.kdg.processor.controllers.web;

import be.kdg.processor.fine.dto.FineDTO;
import be.kdg.processor.offense.offensemodel.Offense;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        String getJson = objectMapper.writeValueAsString(fineDTO);

        mockMvc.perform(get("/finefactors")
                .param("ffDTO", getJson))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/factors.html"))
                .andExpect(model().attribute("ffDTO",
                        hasProperty("emissionFactor")));
    }
}