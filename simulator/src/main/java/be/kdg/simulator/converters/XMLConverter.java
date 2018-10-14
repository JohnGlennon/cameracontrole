package be.kdg.simulator.converters;

import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class XMLConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(XMLConverter.class);
    private XmlMapper mapper = new XmlMapper();

    public Optional<String> convertMessageToXML(CameraMessage message) {
        try {
            return Optional.of(mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            LOGGER.error("Fout bij het converteren van message naar XML.");
        }
        return Optional.empty();
    }
}
