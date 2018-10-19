package be.kdg.processor.message;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.LicensePlate;
import com.fasterxml.jackson.databind.ObjectMapper;
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
