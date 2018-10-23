package be.kdg.processor.fine;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeConverter {

    private final DateTimeFormatter formatter;

    public LocalDateTimeConverter() {
        formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    public LocalDateTime convertStringToLocalDateTime (String string) {
        return LocalDateTime.parse(string, formatter);
    }
}
