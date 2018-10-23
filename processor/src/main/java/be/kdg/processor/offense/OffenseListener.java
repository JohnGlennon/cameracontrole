package be.kdg.processor.offense;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.Car;

import java.time.LocalDateTime;

public interface OffenseListener {
    void listen(Camera camera, Car car, LocalDateTime timestamp);
}
