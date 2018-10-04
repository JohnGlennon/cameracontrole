package be.kdg.processor.receivers;

import be.kdg.processor.deserializers.LicensePlateDeserializer;
import be.kdg.processor.model.Camera;
import be.kdg.processor.deserializers.CameraDeserializer;
import be.kdg.processor.model.LicensePlate;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateNotFoundException;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
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

    private ObjectMapper cameraMapper;
    private SimpleModule cameraModule;

    private ObjectMapper licensePlateMapper;
    private SimpleModule licensePlateModule;

    private CameraServiceProxy cameraServiceProxy;
    private LicensePlateServiceProxy licensePlateServiceProxy;

    public Receiver() {
        queueName = "cameratopic.queue";

        cameraMapper = new ObjectMapper();
        cameraModule = new SimpleModule();
        cameraModule.addDeserializer(Camera.class, new CameraDeserializer());
        cameraMapper.registerModule(cameraModule);

        licensePlateMapper = new ObjectMapper();
        licensePlateModule = new SimpleModule();
        licensePlateModule.addDeserializer(LicensePlate.class, new LicensePlateDeserializer());
        licensePlateMapper.registerModule(licensePlateModule);

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
        } catch (IOException | CameraNotFoundException | LicensePlateNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
