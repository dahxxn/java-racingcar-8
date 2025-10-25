package racingcar.util;

import static racingcar.error.ErrorMessage.CAR_COUNT_ERROR;

import java.util.List;
import racingcar.model.Car;

public final class Validator {
    private static final int MIN_CAR_COUNT = 2;

    public static void validateCarCount(List<Car> cars) {
        if (cars == null || cars.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException(CAR_COUNT_ERROR.getMessage());
        }
    }


}
