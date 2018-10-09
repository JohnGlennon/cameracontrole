package be.kdg.processor.receivers;

import be.kdg.processor.deserializers.JsonDeserializer;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private JsonDeserializer jsonDeserializer;

    private CameraServiceProxy cameraServiceProxy;
    private LicensePlateServiceProxy licensePlateServiceProxy;

    public Receiver() {
        jsonDeserializer = new JsonDeserializer();
        cameraServiceProxy = new CameraServiceProxy();
        licensePlateServiceProxy = new LicensePlateServiceProxy();
    }

    public void receiveMessage(String message) {
        LOGGER.info("Message ontvangen: " + message);

        String[] messageInfo = message.split(" ");
        int cameraId = Integer.parseInt(messageInfo[2]);
        String plateId = messageInfo[3];

        try {
            String cameraInfo = cameraServiceProxy.get(cameraId);
            String licensePlateInfo = licensePlateServiceProxy.get(plateId);
            int euroNorm = jsonDeserializer.toCamera(cameraInfo).getEuroNorm();
            int euroNumber = jsonDeserializer.toLicensePlate(licensePlateInfo).getEuroNumber();
            if (euroNumber < euroNorm) {
                LOGGER.info("OVERTREDING! Uw euroNumber is te laag!");
            }
        } catch (IOException | CameraNotFoundException | LicensePlateNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
