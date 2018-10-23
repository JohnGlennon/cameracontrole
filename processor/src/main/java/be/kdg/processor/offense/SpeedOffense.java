package be.kdg.processor.offense;

import java.time.LocalDateTime;

public class SpeedOffense extends Offense {

    public SpeedOffense(String licensePlate, LocalDateTime timestamp) {
        super(licensePlate, timestamp);
    }
}
