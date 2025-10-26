package racingcar.constant;

public enum GameMessage {
    INPUT_CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    INPUT_ROUND("시도할 횟수는 몇 회인가요?"),

    INPUT_DELIMITER(","),
    OUTPUT_DELIMITER(", "),
    CAR_NAME_SEPARATOR(" : "),

    DISTANCE_MARK("-"),
    NEW_LINE("\n"),

    ROUND_RESULT("실행 결과"),
    FINAL_WINNERS("최종 우승자 : ");

    private final String message;

    GameMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}