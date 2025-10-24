package racingcar.error;

public enum ErrorMessage {
    ROUND_FORMAT_ERROR_NOT_NUMBER("[ERROR] 시도 횟수는 숫자여야 합니다."),

    ;

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
