package be.kdg.processor.camera;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;

public class Segment {
    @JsonProperty("connectedCameraId")
    private int connectedCameraId;

    @JsonProperty("distance")
    private int distance;

    @JsonProperty("speedLimit")
    private int speedLimit;

    public Segment() {
    }

    public Segment(int connectedCameraId, int distance, int speedLimit) {
        this.connectedCameraId = connectedCameraId;
        this.distance = distance;
        this.speedLimit = speedLimit;
    }

    public int getConnectedCameraId() {
        return connectedCameraId;
    }

    public void setConnectedCameraId(int connectedCameraId) {
        this.connectedCameraId = connectedCameraId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }
}
