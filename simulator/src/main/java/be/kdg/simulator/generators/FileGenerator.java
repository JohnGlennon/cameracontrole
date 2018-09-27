package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {

    @Value("${filepath}")
    private String filepath;

    @Override
    public CameraMessage generate() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            List<String> messages = new ArrayList<>();
            String message;
            while ((message = reader.readLine()) != null) {
                messages.add(message);
            }
            String[] info = messages.get(5).split(",");
            return new CameraMessage(Integer.parseInt(info[0]), info[1], LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
