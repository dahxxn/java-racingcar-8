package racingcar.util;

import static racingcar.constant.GameGuide.INPUT_DELIMITER_MARK;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;

public class Parser {
    public static List<Car> getCarList(String carsData) {
        return splitAndTrim(carsData).stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public static int getRoundNumber(String roundData) {
        try {
            return Integer.parseInt(roundData.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());
        }
    }

    private static List<String> splitAndTrim(String carsData) {
        return Arrays.stream(carsData.split(INPUT_DELIMITER_MARK.getMessage()))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
