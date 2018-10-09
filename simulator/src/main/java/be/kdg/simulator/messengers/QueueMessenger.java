package be.kdg.simulator.messengers;

import be.kdg.simulator.config.MessagingConfig;
import be.kdg.simulator.generators.MessageGenerator;
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
    private final RabbitTemplate rabbitTemplate;

    public QueueMessenger(MessageGenerator messageGenerator, RabbitTemplate rabbitTemplate) {
        this.messageGenerator = messageGenerator;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage() {
        try {
            rabbitTemplate.convertAndSend(MessagingConfig.getTopicExchangeName(), MessagingConfig.getQueueName(), messageGenerator.generate().toString());
            LOGGER.info("Message succesvol verzonden.");
        } catch (IllegalArgumentException iae) {
            LOGGER.error("Fout bij het zenden van message.");
        }
    }
}
