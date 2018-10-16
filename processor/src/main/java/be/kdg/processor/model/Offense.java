package be.kdg.processor.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Offense {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cameraId")
    private Camera camera;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_plateId")
    private LicensePlate licensePlate;
    private LocalDateTime timestamp;

    public Offense(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
        this.camera = camera;
        this.licensePlate = licensePlate;
        this.timestamp = timestamp;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
