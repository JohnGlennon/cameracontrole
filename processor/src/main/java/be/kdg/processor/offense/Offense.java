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

    @Column
    private OffenseType offenseType;

    public Offense() {
    }


    public Offense(String licensePlate, LocalDateTime timestamp, OffenseType offenseType) {
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
        this.offenseType = offenseType;
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

    public OffenseType getOffenseType() {
        return offenseType;
    }

    public void setOffenseType(OffenseType offenseType) {
        this.offenseType = offenseType;
    }
}
