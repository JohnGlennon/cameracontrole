package be.kdg.processor.adapters;

import be.kdg.processor.deserializers.JsonDeserializer;
import be.kdg.processor.model.LicensePlate;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LicensePlateServiceAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LicensePlateServiceAdapter.class);

    @Autowired
    private LicensePlateServiceProxy licensePlateServiceProxy;

    @Autowired
    private JsonDeserializer jsonDeserializer;

    public LicensePlate toLicensePlate(String plateId) {
        try {
            String licensePlateInfo = licensePlateServiceProxy.get(plateId);
            return jsonDeserializer.toLicensePlate(licensePlateInfo);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (LicensePlateNotFoundException e) {
            LOGGER.error("License plate niet gevonden: " + plateId);
        } catch (InvalidLicensePlateException e) {
            LOGGER.error("Ongeldige license plate: " + plateId);
        }
        return null;
    }
}
