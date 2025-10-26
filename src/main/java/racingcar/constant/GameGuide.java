package racingcar.constant;

public enum GameGuide {
    INPUT_CAR_NAMES("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"),
    INPUT_ROUND("시도할 횟수는 몇 회인가요?"),
    DISTANCE_MARK("-"),
    DELIMITER_MARK(","),
    ROUND_HISTORY_HEAD("실행 결과"),
    ROUND_HISTORY_NEXT_TO_CAR_NAME(" : "),
    ROUND_HISTORY_NEW_LINE("\n"),
    FINAL_WINNERS("최종 우승자 : ");

    private final String message;

    GameGuide(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
