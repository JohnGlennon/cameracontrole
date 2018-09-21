package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import org.springframework.stereotype.Component;

@Component
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
    public void sendMessage() {
        System.out.println(messageGenerator.generate());
    }
}
