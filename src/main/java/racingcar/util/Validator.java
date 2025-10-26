package racingcar.util;

import static racingcar.error.ErrorMessage.CAR_COUNT_ERROR;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_DUPLICATE;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_EMPTY;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_LONG;
import static racingcar.error.ErrorMessage.ROUND_COUNT_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.model.Car;

public final class Validator {
    private static final int MIN_CAR_COUNT = 2;
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int MIN_ROUND_COUNT = 1;

    public static void validateCarCount(List<Car> cars) {
        if (cars.size() < MIN_CAR_COUNT) {
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

    public static void validateNoDuplicateCarNames(List<Car> cars) {
        Set<String> carNames = new HashSet<>();
        for (Car car : cars) {
            String currentCarName = car.getName();
            if (!carNames.add(currentCarName)) {
                throw new IllegalArgumentException(CAR_NAME_ERROR_DUPLICATE.getMessage());
            }
        }
    }

    public static void validateRoundCount(int roundCount) {
        if (roundCount < MIN_ROUND_COUNT) {
            throw new IllegalArgumentException(ROUND_COUNT_ERROR.getMessage());
        }
    }
}
