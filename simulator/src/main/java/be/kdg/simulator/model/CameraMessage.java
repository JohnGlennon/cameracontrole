package be.kdg.simulator.model;

import be.kdg.simulator.converters.DateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CameraMessage {

    private int id;
    private String licensePlate;
    private LocalDateTime timestamp;
    private long delay;

    public CameraMessage() {
    }

    public CameraMessage(int id, String licensePlate, LocalDateTime timestamp) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
    }

    public CameraMessage(int id, String licensePlate, LocalDateTime timestamp, long delay) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @JsonDeserialize(using = DateTimeSerializer.class)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraMessage that = (CameraMessage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
        String strDate = formatter.format(timestamp);
        return String.format("Camera Message %d %s %s", id, licensePlate, strDate);
    }
}
