package be.kdg.processor.fine;

import be.kdg.processor.camera.CameraServiceAdapter;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.licenseplate.LicensePlateServiceAdapter;
import be.kdg.processor.offense.OffenseListener;
import be.kdg.processor.camera.Camera;
import be.kdg.processor.message.CameraMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ObjectConverter {

    private final CameraServiceAdapter cameraServiceAdapter;
    private final LicensePlateServiceAdapter licensePlateServiceAdapter;
    private final List<OffenseListener> listeners;

    public ObjectConverter(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter, List<OffenseListener> listeners) {
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
        this.listeners = listeners;
    }

    public void convert(CameraMessage cameraMessage) {
        Optional<Camera> camera = cameraServiceAdapter.toCamera(cameraMessage.getId());
        Optional<Car> car = licensePlateServiceAdapter.toCar(cameraMessage.getLicensePlate());
        if (camera.isPresent() && car.isPresent()) {
            listeners.forEach(listener -> listener.listen(camera.get(), car.get(), cameraMessage.getTimestamp()));
        }
    }
}
