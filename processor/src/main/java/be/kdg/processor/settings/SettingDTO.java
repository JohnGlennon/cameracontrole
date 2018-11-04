package be.kdg.processor.settings;

public class SettingDTO {

    private String property;
    private int value;

    public SettingDTO() {
    }

    public SettingDTO(String property, int value) {
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
