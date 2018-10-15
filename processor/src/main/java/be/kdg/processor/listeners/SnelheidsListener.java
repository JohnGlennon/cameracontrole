package be.kdg.processor.listeners;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SnelheidsListener implements OvertredingsListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnelheidsListener.class);

    @Override
    public void listen(Camera camera, LicensePlate licensePlate) {

    }
}
