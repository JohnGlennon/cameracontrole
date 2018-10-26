package be.kdg.processor.camera.cameramodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {
    @JsonProperty("cameraId")
    private int cameraId;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("segment")
    private Segment segment;

    @JsonProperty("euroNorm")
    private int euroNorm;

    public Camera() {
    }

    public Camera(int cameraId, int euroNorm) {
        this.cameraId = cameraId;
        this.euroNorm = euroNorm;
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
