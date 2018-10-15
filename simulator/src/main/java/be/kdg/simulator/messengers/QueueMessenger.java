package be.kdg.simulator.messengers;

import be.kdg.simulator.config.MessagingConfig;
import be.kdg.simulator.converters.XMLConverter;
import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessenger.class);

    private final MessageGenerator messageGenerator;
    private XMLConverter xmlConverter;
    private final RabbitTemplate rabbitTemplate;

    public QueueMessenger(MessageGenerator messageGenerator, XMLConverter xmlConverter, RabbitTemplate rabbitTemplate) {
        this.messageGenerator = messageGenerator;
        this.xmlConverter = xmlConverter;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public CameraMessage getMessage() {
        return messageGenerator.generate().get();
    }

    @Override
    public void sendMessage(CameraMessage cameraMessage) {
        try {
            rabbitTemplate.convertAndSend(MessagingConfig.getTopicExchangeName(), MessagingConfig.getQueueName(), xmlConverter.convertMessageToXML(cameraMessage));
            LOGGER.info("Message succesvol verzonden.");
        } catch (IllegalArgumentException iae) {
            LOGGER.error("Fout bij het zenden van message.");
        } catch (JsonProcessingException e) {
            LOGGER.error("Fout bij het converteren van message naar XML.");
        }
    }
}
