package be.kdg.processor.listeners;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmissieListener implements OvertredingsListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmissieListener.class);

    @Override
    public void listen(Camera camera, LicensePlate licensePlate) {
        int euroNorm = camera.getEuroNorm();
        int euroNumber = licensePlate.getEuroNumber();
        if (euroNumber < euroNorm) {
            LOGGER.info("OVERTREDING! Uw euroNumber is te laag!");
        }
    }
}
