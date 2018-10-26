package be.kdg.processor.message;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.licenseplate.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonDeserializer {
    private final ObjectMapper mapper = new ObjectMapper();

    public Camera toCamera(String json) throws IOException {
        return mapper.readValue(json, Camera.class);
    }

    public Car toCar(String json) throws IOException {
        return mapper.readValue(json, Car.class);
    }
}
