package be.kdg.processor.listeners;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.LicensePlate;

import java.time.LocalDateTime;

public interface OvertredingsListener {
    void listen(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp);
}
