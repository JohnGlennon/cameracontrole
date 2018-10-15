package be.kdg.processor.deserializers;

import be.kdg.processor.model.CameraMessage;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XMLConverter {
    private XmlMapper mapper;

    public XMLConverter() {
        mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public CameraMessage convertXMLToMessage(String xml) throws IOException {
        return mapper.readValue(xml, CameraMessage.class);
    }
}
