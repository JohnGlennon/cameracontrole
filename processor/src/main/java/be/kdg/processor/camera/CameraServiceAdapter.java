package be.kdg.processor.camera;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.message.JsonDeserializer;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.Cacheable;
import java.io.IOException;
import java.util.Optional;

@Component
@Cacheable
public class CameraServiceAdapter {
private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceAdapter.class);

    private final JsonDeserializer jsonDeserializer;
    private final CameraServiceProxy cameraServiceProxy;

    public CameraServiceAdapter(JsonDeserializer jsonDeserializer, CameraServiceProxy cameraServiceProxy) {
        this.jsonDeserializer = jsonDeserializer;
        this.cameraServiceProxy = cameraServiceProxy;
    }

    public Optional<Camera> toCamera(int cameraId) {
        try {
            String cameraInfo = cameraServiceProxy.get(cameraId);
            return Optional.of(jsonDeserializer.toCamera(cameraInfo));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (CameraNotFoundException e) {
            LOGGER.error("Camera not found: " + cameraId);
        }
        return Optional.empty();
    }
}
