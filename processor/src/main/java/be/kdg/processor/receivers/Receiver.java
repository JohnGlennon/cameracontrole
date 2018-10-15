package be.kdg.processor.receivers;

import be.kdg.processor.deserializers.ObjectConverter;
import be.kdg.processor.deserializers.XMLConverter;
import be.kdg.processor.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private XMLConverter xmlConverter;
    private ObjectConverter objectConverter;

    public Receiver(XMLConverter xmlConverter, ObjectConverter objectConverter) {
        this.xmlConverter = xmlConverter;
        this.objectConverter = objectConverter;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Message ontvangen: " + message);

        try {
            CameraMessage cameraMessage = xmlConverter.convertXMLToMessage(message);
            objectConverter.convert(cameraMessage);
        } catch (IOException e) {
            LOGGER.error("Fout bij het converteren van XML naar message.");
        }
    }
}
