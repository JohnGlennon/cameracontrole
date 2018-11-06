package be.kdg.processor.message;

import be.kdg.processor.camera.CameraServiceAdapter;
import be.kdg.processor.camera.cameramodel.Camera;
import be.kdg.processor.fine.FineManager;
import be.kdg.processor.licenseplate.Car;
import be.kdg.processor.licenseplate.LicensePlateServiceAdapter;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CameraServiceAdapter cameraServiceAdapter;
    private LicensePlateServiceAdapter licensePlateServiceAdapter;
    private final XMLConverter xmlConverter;
    private final FineManager fineManager;

    public Receiver(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter, XMLConverter xmlConverter, FineManager fineManager) {
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
        this.xmlConverter = xmlConverter;
        this.fineManager = fineManager;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Message received: " + message);

        try {
            CameraMessage cameraMessage = xmlConverter.convertXMLToMessage(message);
            Camera camera = cameraServiceAdapter.toCamera(cameraMessage.getId());
            Car car = licensePlateServiceAdapter.toCar(cameraMessage.getLicensePlate());

            fineManager.checkForSpeedOffense(cameraMessage, camera);
            fineManager.checkForEmissionOffense(cameraMessage, camera, car);
            //Voor andere types overtredingen: hier  zouden nog methodes van de fineManager aangesproken kunnen worden
            //met cameraMessage, camera en car als parameters.
        } catch (IOException | CameraNotFoundException | LicensePlateNotFoundException | InvalidLicensePlateException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
