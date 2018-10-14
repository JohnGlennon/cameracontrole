package be.kdg.simulator.converters;

import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class XMLConverter {
    private XmlMapper mapper = new XmlMapper();

    public String convertMessageToXML(CameraMessage message) throws JsonProcessingException {
        return mapper.writeValueAsString(message);
    }
}
