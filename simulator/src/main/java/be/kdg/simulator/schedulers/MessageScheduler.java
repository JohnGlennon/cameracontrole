package be.kdg.simulator.schedulers;

import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class MessageScheduler {

    private ScheduledExecutorService scheduler;
    private final Messenger messenger;

    @Value("${normal_frequency}")
    private long normalFrequency;

    @Value("${faster_frequency}")
    private long fasterFrequency;

    private LocalTime beginInterval;

    private LocalTime endInterval;

    public MessageScheduler(Messenger messenger, @Value("${begin_interval}") String beginInterval, @Value("${end_interval}") String endInterval) {
        this.messenger = messenger;
        this.beginInterval = LocalTime.parse(beginInterval);
        this.endInterval = LocalTime.parse(endInterval);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        tick();
    }

    private void tick() {
        CameraMessage message = messenger.getMessage();
        LocalTime time = LocalTime.from(message.getTimestamp());
        long delay = message.getDelay();
        if (delay == -1) {
            if (time.isAfter(beginInterval) && time.isBefore(endInterval)) {
                scheduler.schedule(this::tick, fasterFrequency, TimeUnit.MILLISECONDS);
            } else {
                scheduler.schedule(this::tick, normalFrequency, TimeUnit.MILLISECONDS);
            }
        } else {
            scheduler.schedule(this::tick, delay, TimeUnit.MILLISECONDS);
        }
        messenger.sendMessage(message);
    }
}
