package be.kdg.simulator.messengers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class MessageScheduler {

    private ScheduledExecutorService scheduler;
    private Messenger messenger;

    @Value("${frequentie}")
    private long frequentie;

    public MessageScheduler(Messenger messenger) {
        this.messenger = messenger;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        tick();
    }

    private void tick() {
        scheduler.schedule(this::tick, frequentie, TimeUnit.MILLISECONDS);
        messenger.sendMessage();
    }
}
