package be.kdg.processor.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Boete {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_overtredingsId")
    private Overtreding overtreding;

    private int bedrag;

    public Boete(Overtreding overtreding, int bedrag) {
        this.overtreding = overtreding;
        this.bedrag = bedrag;
    }

    public Overtreding getOvertreding() {
        return overtreding;
    }

    public void setOvertreding(Overtreding overtreding) {
        this.overtreding = overtreding;
    }

    public int getBedrag() {
        return bedrag;
    }

    public void setBedrag(int bedrag) {
        this.bedrag = bedrag;
    }
}
