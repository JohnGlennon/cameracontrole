package be.kdg.processor.fine.dto;

import be.kdg.processor.offense.offensemodel.Offense;
import lombok.Data;

@Data
public class FineDTO {

    private Offense offense;
    private double amount;
    private boolean approved;
    private String motivation;

    public FineDTO() {
    }

    public FineDTO(Offense offense, double amount) {
        this.offense = offense;
        this.amount = amount;
    }

    public void setOffense(Offense offense) {
        this.offense = offense;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Offense getOffense() {
        return offense;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
