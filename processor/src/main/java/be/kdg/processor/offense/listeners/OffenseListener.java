package be.kdg.processor.offense.listeners;

import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.licenseplate.Car;

import java.time.LocalDateTime;

public interface OffenseListener {
    void listen(Camera camera, Car car, LocalDateTime timestamp);
}
