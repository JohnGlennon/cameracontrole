package be.kdg.processor.receivers;

import be.kdg.processor.deserializers.JsonDeserializer;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    private static String queueName;

    private JsonDeserializer jsonDeserializer;

    private CameraServiceProxy cameraServiceProxy;
    private LicensePlateServiceProxy licensePlateServiceProxy;

    public Receiver() {
        queueName = "cameratopic.queue";
        jsonDeserializer = new JsonDeserializer();
        cameraServiceProxy = new CameraServiceProxy();
        licensePlateServiceProxy = new LicensePlateServiceProxy();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    public void receiveMessage(String message) {
        LOGGER.info("Message ontvangen: " + message);

        String[] messageInfo = message.split(" ");
        int cameraId = Integer.parseInt(messageInfo[2]);
        String plateId = messageInfo[3];

        try {
            String cameraInfo = cameraServiceProxy.get(cameraId);
            String licensePlateInfo = licensePlateServiceProxy.get(plateId);
            LOGGER.info(jsonDeserializer.toCamera(cameraInfo).getCameraId() + "");
            LOGGER.info(jsonDeserializer.toLicensePlate(licensePlateInfo).getPlateId());
        } catch (IOException | CameraNotFoundException | LicensePlateNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
