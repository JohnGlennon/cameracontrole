package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessenger.class);

    private final MessageGenerator messageGenerator;
    private final RabbitTemplate rabbitTemplate;

    private static String topicExchangeName = "cameratopic";
    private static String queueName = "cameratopic.queue";

    public QueueMessenger(MessageGenerator messageGenerator, RabbitTemplate rabbitTemplate) {
        this.messageGenerator = messageGenerator;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    @Scheduled(fixedDelayString = "${frequentie}")
    public void sendMessage() {
        try {
            rabbitTemplate.convertAndSend(topicExchangeName, queueName, messageGenerator.generate().toString());
            LOGGER.info("Message succesvol verzonden.");
        } catch (IllegalArgumentException iae) {
            LOGGER.error("Fout bij het zenden van message.");
        }
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("cameratopic.*");
    }
}
