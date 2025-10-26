package racingcar.service;

import static racingcar.constant.GameMessage.CAR_NAME_SEPARATOR;
import static racingcar.constant.GameMessage.NEW_LINE;

import java.util.List;
import racingcar.model.Car;
import racingcar.policy.ForwardPolicy;
import racingcar.record.RaceData;
import racingcar.record.SetupData;
import racingcar.util.Winners;

public class GamePlayService {
    private final ForwardPolicy forwardPolicy;

    public GamePlayService(ForwardPolicy forwardPolicy) {
        this.forwardPolicy = forwardPolicy;
    }

    public RaceData play(SetupData setupData) {
        String roundSnapshot = playAllRounds(setupData);
        String finalWinners = Winners.pickWinners(setupData.cars());
        return new RaceData(roundSnapshot, finalWinners);
    }

    private String playAllRounds(SetupData setupData) {
        StringBuilder roundSnapshot = new StringBuilder();

        for (int round = 0; round < setupData.rounds(); round++) {
            playOneRound(setupData.cars(), roundSnapshot);
        }
        return roundSnapshot.toString();
    }

    private void playOneRound(List<Car> cars, StringBuilder roundSnapshot) {
        for (Car car : cars) {
            car.tryMove(forwardPolicy);
            recordCarDistance(roundSnapshot, car);
        }
        roundSnapshot.append(NEW_LINE.getMessage());
    }

    private void recordCarDistance(StringBuilder roundSnapshot, Car car) {
        roundSnapshot.append(car.getName());
        roundSnapshot.append(CAR_NAME_SEPARATOR.getMessage());
        roundSnapshot.append(car.getDistanceDisplay());
        roundSnapshot.append(NEW_LINE.getMessage());
    }
}
