package be.kdg.processor.fine;

import be.kdg.processor.offense.Offense;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Fine {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_offenseId")
    private Offense offense;

    private int amount;

    private boolean approved;

    public Fine() {
    }

    public Fine(Offense offense, int amount) {
        this.offense = offense;
        this.amount = amount;
    }

    public Fine(Offense offense, int amount, boolean approved) {
        this.offense = offense;
        this.amount = amount;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public Offense getOffense() {
        return offense;
    }

    public void setOffense(Offense offense) {
        this.offense = offense;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
