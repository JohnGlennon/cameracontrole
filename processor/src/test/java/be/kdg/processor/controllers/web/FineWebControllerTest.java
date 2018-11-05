package be.kdg.processor.controllers.web;

import be.kdg.processor.settings.SettingsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FineWebControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void testChangeFineFactor() throws Exception {
//        SettingsDTO settingsDTO = new SettingsDTO("emissionfactor", 50);
//        String postJson = objectMapper.writeValueAsString(settingsDTO);
//
//        mockMvc.perform(post("/fine/finefactor.do")
//                .param("settingsDTO", postJson))
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(view().name("factors"))
//                .andExpect(model().attribute("ffDTO", hasProperty("emissionFactor")));
//    }
}