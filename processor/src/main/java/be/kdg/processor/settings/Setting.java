package be.kdg.processor.settings;

import javax.persistence.Column;
import javax.persistence.Id;

public class Setting {

    @Id
    private String property;

    @Column
    private String value;
}
