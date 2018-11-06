package be.kdg.processor.licenseplate;

import be.kdg.processor.message.JsonDeserializer;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.Cacheable;
import java.io.IOException;

@Component
@Cacheable
public class LicensePlateServiceAdapter {

    private final LicensePlateServiceProxy licensePlateServiceProxy;
    private final JsonDeserializer jsonDeserializer;
    private final RetryTemplate retryTemplate;

    private String carInfo;

    public LicensePlateServiceAdapter(LicensePlateServiceProxy licensePlateServiceProxy, JsonDeserializer jsonDeserializer, RetryTemplate retryTemplate) {
        this.licensePlateServiceProxy = licensePlateServiceProxy;
        this.jsonDeserializer = jsonDeserializer;
        this.retryTemplate = retryTemplate;
    }

    public Car toCar(String plateId) throws IOException, LicensePlateNotFoundException, InvalidLicensePlateException {
        retryTemplate.execute(context -> {
            carInfo = licensePlateServiceProxy.get(plateId);
            return jsonDeserializer.toCar(carInfo);
        });
        return jsonDeserializer.toCar(carInfo);
    }
}
