package racingcar.model;

import static racingcar.constant.GameGuide.DISTANCE_MARK;

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

    public String getDistance() {
        return DISTANCE_MARK.getMessage().repeat(distance);
    }

    public void addDistance() {
        this.distance++;
    }
}
