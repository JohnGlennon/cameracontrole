package be.kdg.processor.settings;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Settings {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String property;

    @Column
    private int value;

    public Settings() {
    }

    public Settings(String property, int value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
