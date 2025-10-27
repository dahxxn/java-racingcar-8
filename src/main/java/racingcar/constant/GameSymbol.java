package racingcar.constant;

public enum GameSymbol {
    INPUT_DELIMITER(","),
    OUTPUT_DELIMITER(", "),
    CAR_NAME_SEPARATOR(" : "),
    DISTANCE_MARK("-");

    private final String value;

    GameSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}