package racingcar.util;

import static racingcar.error.ErrorMessage.CAR_COUNT_ERROR;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_EMPTY;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_LONG;

import java.util.List;
import racingcar.model.Car;

public final class Validator {
    private static final int MIN_CAR_COUNT = 2;
    private static final int MAX_CAR_NAME_LENGTH = 5;

    public static void validateCarCount(List<Car> cars) {
        if (cars == null || cars.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException(CAR_COUNT_ERROR.getMessage());
        }
    }

    public static void validateCarName(String carName) {
        if (carName.isEmpty()) {
            throw new IllegalArgumentException(CAR_NAME_ERROR_EMPTY.getMessage());
        }

        if (carName.length() > MAX_CAR_NAME_LENGTH) {
            throw new IllegalArgumentException(CAR_NAME_ERROR_LONG.getMessage());
        }
    }


}
