package be.kdg.processor.model;

public class Camera {
    private int cameraId;
    private double latitude;
    private double longitude;
    private int euroNorm;

    public Camera(int cameraId, double latitude, double longitude, int euroNorm) {
        this.cameraId = cameraId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.euroNorm = euroNorm;
    }

    public int getCameraId() {
        return cameraId;
    }

    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getEuroNorm() {
        return euroNorm;
    }

    public void setEuroNorm(int euroNorm) {
        this.euroNorm = euroNorm;
    }
}
