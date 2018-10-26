package be.kdg.processor.fine.dto;

import lombok.Data;

@Data
public class FineFactorDTO {

    private int emissionFactor;
    private int speedFactor;

    public FineFactorDTO() {
    }

    public FineFactorDTO(int emissionFactor, int speedFactor) {
        this.emissionFactor = emissionFactor;
        this.speedFactor = speedFactor;
    }

    public int getEmissionFactor() {
        return emissionFactor;
    }

    public void setEmissionFactor(int emissionFactor) {
        this.emissionFactor = emissionFactor;
    }

    public int getSpeedFactor() {
        return speedFactor;
    }

    public void setSpeedFactor(int speedFactor) {
        this.speedFactor = speedFactor;
    }
}
