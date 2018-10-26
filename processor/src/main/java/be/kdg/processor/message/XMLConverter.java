package be.kdg.processor.message;

import be.kdg.processor.message.CameraMessage;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XMLConverter {
    private final XmlMapper mapper;

    public XMLConverter() {
        mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public CameraMessage convertXMLToMessage(String xml) throws IOException {
        return mapper.readValue(xml, CameraMessage.class);
    }
}
