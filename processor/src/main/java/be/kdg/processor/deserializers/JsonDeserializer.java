package be.kdg.processor.deserializers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.LicensePlate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonDeserializer {
    private ObjectMapper mapper = new ObjectMapper();

    public Camera toCamera(String json) throws IOException {
        return mapper.readValue(json, Camera.class);
    }

    public LicensePlate toLicensePlate(String json) throws IOException {
        return mapper.readValue(json, LicensePlate.class);
    }
}
