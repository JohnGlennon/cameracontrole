package be.kdg.processor.offense;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.LicensePlate;

import java.time.LocalDateTime;

public class SpeedOffense extends Offense {

    public SpeedOffense(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        super(camera, licensePlate, timestamp);
    }
}
