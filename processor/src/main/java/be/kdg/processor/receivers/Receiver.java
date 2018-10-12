package be.kdg.processor.receivers;

import be.kdg.processor.adapters.CameraServiceAdapter;
import be.kdg.processor.adapters.LicensePlateServiceAdapter;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.LicensePlate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CameraServiceAdapter cameraServiceAdapter;
    private LicensePlateServiceAdapter licensePlateServiceAdapter;

    public Receiver(CameraServiceAdapter cameraServiceAdapter, LicensePlateServiceAdapter licensePlateServiceAdapter) {
        this.cameraServiceAdapter = cameraServiceAdapter;
        this.licensePlateServiceAdapter = licensePlateServiceAdapter;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Message ontvangen: " + message);

        String[] messageInfo = message.split(" ");
        int cameraId = Integer.parseInt(messageInfo[2]);
        String plateId = messageInfo[3];

        Optional<Camera> camera = cameraServiceAdapter.toCamera(cameraId);
        Optional<LicensePlate> licensePlate = licensePlateServiceAdapter.toLicensePlate(plateId);

        if (camera.isPresent() && licensePlate.isPresent()) {
            int euroNorm = camera.get().getEuroNorm();
            int euroNumber = licensePlate.get().getEuroNumber();
            if (euroNumber < euroNorm) {
                LOGGER.info("OVERTREDING! Uw euroNumber is te laag!");
            }
        }
    }
}
