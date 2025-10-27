package racingcar.util;

import static racingcar.constant.GameSymbol.OUTPUT_DELIMITER;

import java.util.List;
import racingcar.model.Car;

public final class Winners {
    private static final int DEFAULT_DISTANCE = 0;

    private Winners() {
    }

    public static String pickWinners(List<Car> cars) {
        int maxDistance = findMaxDistance(cars);
        return collectWinnerNames(cars, maxDistance);
    }

    private static String collectWinnerNames(List<Car> cars, int maxDistance) {
        StringBuilder winnerNames = new StringBuilder();
        boolean isFirst = true;

        for (Car car : cars) {
            if (car.getDistance() == maxDistance) {
                String carName = car.getName();
                appendWinnerName(winnerNames, carName, isFirst);
                isFirst = false;
            }
        }

        return winnerNames.toString();
    }

    private static void appendWinnerName(StringBuilder winnerNames, String carName, boolean isFirst) {
        if (!isFirst) {
            String delimiter = OUTPUT_DELIMITER.getValue();
            winnerNames.append(delimiter);
        }

        winnerNames.append(carName);
    }

    private static int findMaxDistance(List<Car> cars) {
        int maxDistance = DEFAULT_DISTANCE;
        for (Car car : cars) {
            int distance = car.getDistance();
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }
}