package be.kdg.processor.listeners;

import be.kdg.processor.managers.FineManager;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.EmissionOffense;
import be.kdg.processor.model.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmissionListener implements OffenseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmissionListener.class);
    private FineManager fineManager;

    public EmissionListener(FineManager fineManager) {
        this.fineManager = fineManager;
    }

    @Override
    public boolean listen(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        int euroNorm = camera.getEuroNorm();
        int euroNumber = licensePlate.getEuroNumber();
        if (euroNumber < euroNorm) {
            LOGGER.info("Emission Offense! Your euro number is too low!");
            fineManager.calculateFine(new EmissionOffense(camera, licensePlate, timestamp));
            return true;
        }
        return false;
    }
}
