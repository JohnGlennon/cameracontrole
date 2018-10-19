package be.kdg.processor.offense;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.LicensePlate;

import java.time.LocalDateTime;

public class EmissionOffense extends Offense {

    public EmissionOffense(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        super(camera, licensePlate, timestamp);
    }
}