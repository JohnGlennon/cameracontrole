package be.kdg.processor.offense;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class Offense {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String licensePlate;

    @Column
    private LocalDateTime timestamp;

    public Offense() {
    }


    public Offense(String licensePlate, LocalDateTime timestamp) {
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
