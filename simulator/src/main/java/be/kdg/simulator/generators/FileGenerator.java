package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    private String filepath;
    private List<CameraMessage> cameraMessages;
    private static int counter;

    public FileGenerator(@Value("${filepath}") String filepath) {
        this.filepath = filepath;
        cameraMessages = new ArrayList<>();
        counter = 0;
        readFile();
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String message = reader.readLine();
            LocalDateTime localDateTime = LocalDateTime.now();
            while (message != null) {
                String[] info = message.split(",");
                localDateTime = localDateTime.plusNanos(Integer.parseInt(info[2]) * 1000000);
                cameraMessages.add(new CameraMessage(Integer.parseInt(info[0]), info[1], localDateTime, Integer.parseInt(info[2])));
                message = reader.readLine();
            }
            LOGGER.info("File succesvol gelezen van: " + filepath + ".");
        } catch (IOException e) {
            LOGGER.error("Fout bij het lezen van file: " + filepath + ".");
        }
    }

    @Override
    public CameraMessage generate() {
        if (counter < cameraMessages.size()) {
            counter++;
            return cameraMessages.get(counter - 1);
        }
        return null;
    }
}
