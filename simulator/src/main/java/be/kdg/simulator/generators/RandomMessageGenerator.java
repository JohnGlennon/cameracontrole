package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {

    @Value("${maximum}")
    private int maximum;

    @Override
    public Optional<CameraMessage> generate() {
        Random random = new Random();

        int id = random.nextInt(maximum) + 1;

        int[] cijfers = new int[4];
        char[] chars = new char[3];

        for (int i = 0; i < 4; i++) {
            cijfers[i] = random.nextInt(10);
        }

        for (int i = 0; i < 3; i++) {
            chars[i] = (char) (random.nextInt(26) + 65);
        }

        String licensePlate = String.format("%d-%c%c%c-%d%d%d", cijfers[0], chars[0], chars[1], chars[2], cijfers[1], cijfers[2], cijfers[3]);

        return Optional.of(new CameraMessage(id, licensePlate, LocalDateTime.now()));
    }
}
