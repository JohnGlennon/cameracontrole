package be.kdg.processor.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class SnelheidsOvertreding extends Overtreding {

    public SnelheidsOvertreding(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        super(camera, licensePlate, timestamp);
    }
}
