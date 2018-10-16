package be.kdg.processor.model;

import java.time.LocalDateTime;

public class EmissieOvertreding extends Overtreding {

    public EmissieOvertreding(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        super(camera, licensePlate, timestamp);
    }
}
