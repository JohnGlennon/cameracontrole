package be.kdg.processor.model;

import java.time.LocalDateTime;

public class EmissionOffense extends Offense {

    public EmissionOffense(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        super(camera, licensePlate, timestamp);
    }
}
