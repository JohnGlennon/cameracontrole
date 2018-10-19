package be.kdg.processor.offense;

import be.kdg.processor.camera.Camera;
import be.kdg.processor.licenseplate.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpeedListener implements OffenseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpeedListener.class);

    @Override
    public boolean listen(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        return false;
    }
}
