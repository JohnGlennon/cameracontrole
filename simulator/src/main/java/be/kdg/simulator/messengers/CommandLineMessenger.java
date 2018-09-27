package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "commandLine")
//APPLICATION FAILED TO START oplossen met:
//a) @Qualifier gebruiken
//b) @ConditionalOnProperty gebruiken
public class CommandLineMessenger implements Messenger {

    private final MessageGenerator messageGenerator;

    //@AutoWired niet meer nodig door @Component, dit is Constructor Injection
    public CommandLineMessenger(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    @Override
    @Scheduled(fixedDelayString = "${frequentie}")
    public void sendMessage() {
        System.out.println(messageGenerator.generate());
    }
}
