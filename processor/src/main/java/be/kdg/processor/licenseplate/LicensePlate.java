package be.kdg.processor.licenseplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class LicensePlate {
    @Id
    @JsonProperty("plateId")
    private String plateId;
    @JsonProperty("nationalNumber")
    private String nationalNumber;
    @JsonProperty("euroNumber")
    private int euroNumber;

    public LicensePlate() {
    }

    public LicensePlate(String plateId, int euroNumber) {
        this.plateId = plateId;
        this.euroNumber = euroNumber;
    }

    public LicensePlate(String plateId, String nationalNumber, int euroNumber) {
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
