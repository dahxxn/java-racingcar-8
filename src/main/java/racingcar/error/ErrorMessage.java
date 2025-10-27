package racingcar.error;

public enum ErrorMessage {
    ROUND_FORMAT_ERROR_NOT_NUMBER("[ERROR] 시도 횟수는 숫자여야 합니다."),
    ROUND_COUNT_ERROR("[ERROR] 시도 횟수는 1 이상이어야 합니다."),

    CAR_COUNT_ERROR("[ERROR] 자동차는 2대 이상이어야 합니다."),
    CAR_NAME_ERROR_EMPTY("[ERROR] 자동차 이름은 1글자 이상이어야 합니다."),
    CAR_NAME_ERROR_WHITESPACE("[ERROR] 자동차 이름에 공백을 포함할 수 없습니다."),
    CAR_NAME_ERROR_LONG("[ERROR] 자동차 이름은 5자 이하여야 합니다."),
    CAR_NAME_ERROR_DUPLICATE("[ERROR] 자동차 이름은 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
