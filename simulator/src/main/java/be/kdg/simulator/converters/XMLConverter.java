package be.kdg.simulator.converters;

import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class XMLConverter {
    private final XmlMapper mapper;

    public XMLConverter() {
        mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public String convertMessageToXML(CameraMessage message) throws JsonProcessingException {
        return mapper.writeValueAsString(message);
    }
}
