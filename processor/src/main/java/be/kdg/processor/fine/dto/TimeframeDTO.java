package be.kdg.processor.fine.dto;

public class TimeframeDTO {

    private int emissionTimeframe;
    private int speedTimeframe;

    public TimeframeDTO() {
    }

    public TimeframeDTO(int emissionTimeframe, int speedTimeframe) {
        this.emissionTimeframe = emissionTimeframe;
        this.speedTimeframe = speedTimeframe;
    }

    public int getEmissionTimeframe() {
        return emissionTimeframe;
    }

    public void setEmissionTimeframe(int emissionTimeframe) {
        this.emissionTimeframe = emissionTimeframe;
    }

    public int getSpeedTimeframe() {
        return speedTimeframe;
    }

    public void setSpeedTimeframe(int speedTimeframe) {
        this.speedTimeframe = speedTimeframe;
    }
}
