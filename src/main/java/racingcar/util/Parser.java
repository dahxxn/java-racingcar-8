package racingcar.util;

import static racingcar.constant.GameGuide.DELIMITER_MARK;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;

public class Parser {
    public static List<Car> getCarList(String carsData) {
        List<String> carNames = splitAndTrim(carsData);

        List<Car> cars = new ArrayList<>();
        for (String carName : carNames) {
            cars.add(new Car(carName));
        }
        return cars;
    }

    public static int getRoundNumber(String roundData) {
        try {
            return Integer.parseInt(roundData.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());
        }
    }

    private static List<String> splitAndTrim(String carsData) {
        return Arrays.stream(carsData.split(DELIMITER_MARK.getMessage()))
                .map(String::trim)
                .collect(Collectors.toList());
    }

}
