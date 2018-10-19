package be.kdg.processor.offense;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.LicensePlate;

import java.time.LocalDateTime;

public interface OffenseListener {
    boolean listen(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp);
}
