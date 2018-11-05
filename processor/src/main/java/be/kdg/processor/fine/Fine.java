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

    @Column
    private double amount;

    @Column
    private boolean approved;

    @Column
    private String motivation;

    public Fine() {
    }

    public Fine(Long id, Offense offense, double amount) {
        this.id = id;
        this.offense = offense;
        this.amount = amount;
    }

    public Fine(Offense offense, double amount) {
        this.offense = offense;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
