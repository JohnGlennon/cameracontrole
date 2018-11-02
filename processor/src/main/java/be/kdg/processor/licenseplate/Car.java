package be.kdg.processor.licenseplate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    @JsonProperty("plateId")
    private String plateId;

    @JsonProperty("nationalNumber")
    private String nationalNumber;

    @JsonProperty("euroNumber")
    private int euroNumber;

    public Car() {
    }

    public Car(String plateId, String nationalNumber, int euroNumber) {
        this.plateId = plateId;
        this.nationalNumber = nationalNumber;
        this.euroNumber = euroNumber;
    }

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public int getEuroNumber() {
        return euroNumber;
    }

    public void setEuroNumber(int euroNumber) {
        this.euroNumber = euroNumber;
    }
}
