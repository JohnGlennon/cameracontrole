package be.kdg.processor.adapters;

import be.kdg.processor.deserializers.JsonDeserializer;
import be.kdg.processor.model.Camera;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CameraServiceAdapter {
private static final Logger LOGGER = LoggerFactory.getLogger(CameraServiceAdapter.class);

    @Autowired
    private JsonDeserializer jsonDeserializer;

    @Autowired
    private CameraServiceProxy cameraServiceProxy;

    public Camera getCamera(int cameraId) {
        try {
            String cameraInfo = cameraServiceProxy.get(cameraId);
            return jsonDeserializer.toCamera(cameraInfo);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (CameraNotFoundException e) {
            LOGGER.error("Camera niet gevonden: " + cameraId);
        }
        return null;
    }
}
