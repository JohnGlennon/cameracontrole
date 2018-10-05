package be.kdg.processor.deserializers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.LicensePlate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonDeserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDeserializer.class);
    private ObjectMapper mapper = new ObjectMapper();

    public Camera toCamera(String json) {
        try {
            return mapper.readValue(json, Camera.class);
        } catch (IOException e) {
            LOGGER.error("Fout bij het lezen van Json.");
        }
        return null;
    }

    public LicensePlate toLicensePlate(String json) {
        try {
            return mapper.readValue(json, LicensePlate.class);
        } catch (IOException e) {
            LOGGER.error("Fout bij het lezen van Json.");
        }
        return null;
    }
}
