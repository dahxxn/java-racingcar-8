package racingcar.error;

public enum ErrorMessage {
    ROUND_FORMAT_ERROR_NOT_NUMBER("[ERROR] 시도 횟수는 숫자여야 합니다."),
    CAR_COUNT_ERROR("[ERROR] 경주 게임에 참여하는 자동차 수는 2 이상이어야 합니다."),
    CAR_NAME_ERROR_EMPTY("[ERROR] 경주 게임에 참여하는 자동차 이름은 1글자 이상이어야 합니다."),
    CAR_NAME_ERROR_LONG("[ERROR] 경주 게임에 참여하는 자동차 이름은 5자 이하여야 합니다."),
    CAR_NAME_ERROR_DUPLICATE("[ERROR] 경주 게임에 참여하는 자동차 이름은 서로 달라야 합니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
