package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    private String filepath;
    private BufferedReader reader;
    private LocalDateTime localDateTime;

    public FileGenerator(@Value("${filepath}") String filepath) {
        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            LOGGER.error("File niet gevonden: " + filepath + ".");
        }
        this.filepath = filepath;
        this.localDateTime = LocalDateTime.now();
    }

    @Override
    public Optional<CameraMessage> generate() {
        try {
            String message = reader.readLine();
            String[] info = message.split(",");
            localDateTime = localDateTime.plusNanos(Integer.parseInt(info[2]) * 1000000);
            return Optional.of(new CameraMessage(Integer.parseInt(info[0]), info[1], localDateTime, Integer.parseInt(info[2])));
        } catch (IOException e) {
            LOGGER.error("Fout bij het lezen van file: " + filepath + ".");
        }
        return Optional.empty();
    }
}
