package be.kdg.processor.fine;

import be.kdg.processor.offense.Offense;
import lombok.Data;

@Data
public class FineDTO {

    private Offense offense;
    private int amount;

    public FineDTO() {
    }

    public FineDTO(Offense offense, int amount) {
        this.offense = offense;
        this.amount = amount;
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

    public String toString() {
        return String.valueOf(amount);
    }
}
