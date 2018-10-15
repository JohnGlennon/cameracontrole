package be.kdg.simulator;

import be.kdg.simulator.schedulers.MessageScheduler;
import org.springframework.stereotype.Component;

@Component
public class Simulator {
    private final MessageScheduler scheduler;

    public Simulator(MessageScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void sendMessage() {
        scheduler.startup();
    }
}
