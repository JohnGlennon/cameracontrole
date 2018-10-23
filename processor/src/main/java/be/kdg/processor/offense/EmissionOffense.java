package be.kdg.processor.offense;

import java.time.LocalDateTime;

public class EmissionOffense extends Offense {

    public EmissionOffense(String licensePlate, LocalDateTime timestamp) {
        super(licensePlate, timestamp);
    }
}
