package racingcar.model;

import static racingcar.constant.GameSymbol.DISTANCE_MARK;

import racingcar.policy.ForwardPolicy;

public class Car {
    private final String name;
    private int distance;

    public Car(String name) {
        this.name = name;
        this.distance = 0;
    }

    public String getName() {
        return name;
    }

    public void tryMove(ForwardPolicy forwardPolicy) {
        if (forwardPolicy.canMove()) {
            this.distance++;
        }
    }

    public String getDistanceDisplay() {
        String distanceMark = DISTANCE_MARK.getValue();
        return distanceMark.repeat(distance);
    }

    public int getDistance() {
        return distance;
    }
}
