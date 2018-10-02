package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageScheduler {

    private ScheduledExecutorService scheduler;
    private Messenger messenger;
    private MessageGenerator messageGenerator;

    public MessageScheduler(Messenger messenger, MessageGenerator messageGenerator) {
        this.messenger = messenger;
        this.messageGenerator = messageGenerator;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        tick();
    }

    private void tick() {
        scheduler.schedule(this::tick, getDelay(), TimeUnit.MILLISECONDS);
        messenger.sendMessage();
    }

    private long getDelay() {
        return 1000;
    }
}
