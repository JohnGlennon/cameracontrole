package be.kdg.processor.deserializers;

import be.kdg.processor.adapters.CameraServiceAdapter;
import be.kdg.processor.adapters.LicensePlateServiceAdapter;
import be.kdg.processor.listeners.OvertredingsListener;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.LicensePlate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ObjectConverter {

    private CameraServiceAdapter cameraServiceAdapter;
    private LicensePlateServiceAdapter licensePlateServiceAdapter;
    private List<OvertredingsListener> listeners;

    public ObjectConverter(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter, List<OvertredingsListener> listeners) {
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
        this.listeners = listeners;
    }

    public void convert(CameraMessage cameraMessage) {
        Optional<Camera> camera = cameraServiceAdapter.toCamera(cameraMessage.getId());
        Optional<LicensePlate> licensePlate = licensePlateServiceAdapter.toLicensePlate(cameraMessage.getLicensePlate());
        if (camera.isPresent() && licensePlate.isPresent()) {
            listeners.forEach(listener -> listener.listen(camera.get(), licensePlate.get()));
        }
    }
}
