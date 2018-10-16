package be.kdg.processor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Camera {
    @Id
    @JsonProperty("cameraId")
    private int cameraId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_locationId")
    @JsonProperty("location")
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_segmentCameraId")
    @JsonProperty("segment")
    private Segment segment;
    @JsonProperty("euroNorm")
    private int euroNorm;

    public Camera() {
    }

    public Camera(int cameraId, Location location, Segment segment, int euroNorm) {
        this.cameraId = cameraId;
        this.location = location;
        this.segment = segment;
        this.euroNorm = euroNorm;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public int getEuroNorm() {
        return euroNorm;
    }

    public void setEuroNorm(int euroNorm) {
        this.euroNorm = euroNorm;
    }
}
