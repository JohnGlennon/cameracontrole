package be.kdg.processor.listeners;

import be.kdg.processor.managers.BoeteManager;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.EmissieOvertreding;
import be.kdg.processor.model.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmissieListener implements OvertredingsListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmissieListener.class);
    private BoeteManager boeteManager;

    public EmissieListener(BoeteManager boeteManager) {
        this.boeteManager = boeteManager;
    }

    @Override
    public void listen(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        int euroNorm = camera.getEuroNorm();
        int euroNumber = licensePlate.getEuroNumber();
        if (euroNumber < euroNorm) {
            LOGGER.info("OVERTREDING! Uw euroNumber is te laag!");
            boeteManager.berekenBoete(new EmissieOvertreding(camera, licensePlate, timestamp));
        }
    }
}
