package be.kdg.processor.dto;

import be.kdg.processor.model.Offense;
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

    public Offense getOffense() {
        return offense;
    }

    public int getAmount() {
        return amount;
    }
}
