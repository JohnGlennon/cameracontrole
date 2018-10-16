package be.kdg.processor.model;

import java.time.LocalDateTime;

public class EmissieOvertreding implements Overtreding {
    private Camera camera;
    private LicensePlate licensePlate;
    private LocalDateTime timestamp;

    public EmissieOvertreding(Camera camera, LicensePlate licensePlate, LocalDateTime timestamp) {
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
