package be.kdg.processor.fine;

import be.kdg.processor.offense.Offense;
import lombok.Data;

@Data
public class FineDTO {

    private Offense offense;
    private int amount;
    private boolean approved;

    public FineDTO() {
    }

    public FineDTO(Offense offense, int amount) {
        this.offense = offense;
        this.amount = amount;
    }

    public FineDTO(Offense offense, int amount, boolean approved) {
        this.offense = offense;
        this.amount = amount;
        this.approved = approved;
    }

    public void setOffense(Offense offense) {
        this.offense = offense;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Offense getOffense() {
        return offense;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String toString() {
        return String.valueOf(amount);
    }
}
