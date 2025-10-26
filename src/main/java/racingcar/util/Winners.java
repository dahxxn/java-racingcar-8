package racingcar.util;

import static racingcar.constant.GameGuide.DELIMITER_MARK;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.Car;

public class Winners {
    private static final int DEFAULT_DISTANCE = 0;

    public static String pickWinners(List<Car> cars) {
        int maxDistance = findMaxDistance(cars);
        return collectWinnerNames(cars, maxDistance);
    }

    private static String collectWinnerNames(List<Car> cars, int maxDistance) {
        return cars.stream()
                .filter(car -> car.getDistance() == maxDistance)
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER_MARK.getMessage()));
    }

    private static int findMaxDistance(List<Car> cars) {
        return cars.stream().mapToInt(Car::getDistance).max().orElse(DEFAULT_DISTANCE);
    }
}
