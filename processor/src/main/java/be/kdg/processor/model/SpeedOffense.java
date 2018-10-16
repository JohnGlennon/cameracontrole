package be.kdg.processor.model;

import java.time.LocalDateTime;

public class SpeedOffense extends Offense {

    public SpeedOffense(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        super(camera, licensePlate, timestamp);
    }
}
