package racingcar.util;

import static racingcar.constant.GameSymbol.INPUT_DELIMITER;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import java.util.ArrayList;
import java.util.List;
import racingcar.model.Car;

public class Parser {
    public static List<Car> getCarList(String carsData) {
        List<String> carNames = splitAndTrim(carsData);

        List<Car> cars = new ArrayList<>();
        for (String name : carNames) {
            cars.add(new Car(name));
        }
        return cars;
    }

    public static int getRoundNumber(String roundData) {
        try {
            String roundNumber = roundData.trim();
            return Integer.parseInt(roundNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());
        }
    }

    private static List<String> splitAndTrim(String carsData) {
        String delimiter = INPUT_DELIMITER.getValue();

        List<String> carNames = new ArrayList<>();
        for (String part : carsData.split(delimiter)) {
            carNames.add(part.trim());
        }
        return carNames;
    }
}