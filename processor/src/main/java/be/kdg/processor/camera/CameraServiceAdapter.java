package be.kdg.processor.camera;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.message.JsonDeserializer;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.Cacheable;
import java.io.IOException;

@Component
@Cacheable
public class CameraServiceAdapter {

    private final JsonDeserializer jsonDeserializer;
    private final CameraServiceProxy cameraServiceProxy;
    private final RetryTemplate retryTemplate;

    private String cameraInfo;

    public CameraServiceAdapter(JsonDeserializer jsonDeserializer, CameraServiceProxy cameraServiceProxy, RetryTemplate retryTemplate) {
        this.jsonDeserializer = jsonDeserializer;
        this.cameraServiceProxy = cameraServiceProxy;
        this.retryTemplate = retryTemplate;
    }

    public Camera toCamera(int cameraId) throws IOException, CameraNotFoundException {
        retryTemplate.execute(context -> {
            cameraInfo = cameraServiceProxy.get(cameraId);
            return jsonDeserializer.toCamera(cameraInfo);
        });
        return jsonDeserializer.toCamera(cameraInfo);
    }
}
