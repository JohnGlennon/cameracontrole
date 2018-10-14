package be.kdg.processor.deserializers;

import be.kdg.processor.model.CameraMessage;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class XMLConverter {
    private XmlMapper mapper = new XmlMapper();

    public CameraMessage convertXMLToMessage(String xml) throws IOException {
        return mapper.readValue(xml, CameraMessage.class);
    }
}
