package hr.tis.academy.model;

public enum AttractionType {

    MUZEJ("MUZEJ", "Muzej"),
    ULICATRG("ULICATRG", "Ulica/Trg"),
    PARK("PARK", "Park");

    private final String code;
    private final String displayValue;

    AttractionType(String code, String displayValue) {
        this.code = code;
        this.displayValue = displayValue;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
