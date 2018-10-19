package be.kdg.processor.licenseplate;

import be.kdg.processor.message.JsonDeserializer;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class LicensePlateServiceAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LicensePlateServiceAdapter.class);

    private LicensePlateServiceProxy licensePlateServiceProxy;
    private JsonDeserializer jsonDeserializer;

    public LicensePlateServiceAdapter(LicensePlateServiceProxy licensePlateServiceProxy, JsonDeserializer jsonDeserializer) {
        this.licensePlateServiceProxy = licensePlateServiceProxy;
        this.jsonDeserializer = jsonDeserializer;
    }

    public Optional<LicensePlate> toLicensePlate(String plateId) {
        try {
            String licensePlateInfo = licensePlateServiceProxy.get(plateId);
            return Optional.of(jsonDeserializer.toLicensePlate(licensePlateInfo));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (LicensePlateNotFoundException e) {
            LOGGER.error("License plate not found: " + plateId);
        } catch (InvalidLicensePlateException e) {
            LOGGER.error("Invalid license plate: " + plateId);
        }
        return Optional.empty();
    }
}