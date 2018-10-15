package be.kdg.processor.receivers;

import be.kdg.processor.adapters.CameraServiceAdapter;
import be.kdg.processor.adapters.LicensePlateServiceAdapter;
import be.kdg.processor.deserializers.XMLConverter;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CameraServiceAdapter cameraServiceAdapter;
    private LicensePlateServiceAdapter licensePlateServiceAdapter;
    private XMLConverter xmlConverter;

    public Receiver(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter, XMLConverter xmlConverter) {
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
        this.xmlConverter = xmlConverter;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Message ontvangen: " + message);

        try {
            CameraMessage cameraMessage = xmlConverter.convertXMLToMessage(message);

            Optional<Camera> camera = cameraServiceAdapter.toCamera(cameraMessage.getId());
            Optional<LicensePlate> licensePlate = licensePlateServiceAdapter.toLicensePlate(cameraMessage.getLicensePlate());

            if (camera.isPresent() && licensePlate.isPresent()) {
                int euroNorm = camera.get().getEuroNorm();
                int euroNumber = licensePlate.get().getEuroNumber();
                if (euroNumber < euroNorm) {
                    LOGGER.info("OVERTREDING! Uw euroNumber is te laag!");
                }
            }
        } catch (IOException e) {
            LOGGER.error("Fout bij het converteren van XML naar message.");
        }
    }
}
