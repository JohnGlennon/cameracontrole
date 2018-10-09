package be.kdg.simulator;

import be.kdg.simulator.messengers.Messenger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Simulator {
    private final Messenger messenger;

    public Simulator(Messenger messenger) {
        this.messenger = messenger;
    }

    @Scheduled(fixedDelayString = "${frequentie}")
    public void sendMessage() {
        messenger.sendMessage();
    }
}
