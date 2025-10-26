package racingcar.util;

import static racingcar.constant.GameGuide.DELIMITER_MARK;

import java.util.List;
import racingcar.model.Car;

public class Winners {
    private static final int DEFAULT_DISTANCE = 0;

    public static String pickWinners(List<Car> cars) {
        int maxDistance = findMaxDistance(cars);
        return getAllWinners(cars, maxDistance);
    }

    private static String getAllWinners(List<Car> cars, int maxDistance) {
        StringBuilder finalWinners = new StringBuilder();

        for (Car car : cars) {
            if (car.getDistance() == maxDistance) {
                finalWinners.append(car.getName());
                finalWinners.append(DELIMITER_MARK.getMessage());
            }
        }

        if (!finalWinners.isEmpty()) {
            finalWinners.deleteCharAt(finalWinners.length() - 1);
        }

        return finalWinners.toString();
    }

    private static int findMaxDistance(List<Car> cars) {
        return cars.stream().mapToInt(Car::getDistance).max().orElse(DEFAULT_DISTANCE);
    }
}
