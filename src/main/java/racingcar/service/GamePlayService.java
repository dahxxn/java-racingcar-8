package racingcar.service;

import static racingcar.constant.GameGuide.ROUND_HISTORY_NEW_LINE;
import static racingcar.constant.GameGuide.ROUND_HISTORY_NEXT_TO_CAR_NAME;

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
        String roundSnapShot = playAllRounds(setupData);

        String finalWinners = Winners.pickWinners(setupData.cars());

        return new RaceData(roundSnapShot, finalWinners);
    }

    private String playAllRounds(SetupData setupData) {
        StringBuilder roundSnapShot = new StringBuilder();

        for (int round = 1; round <= setupData.rounds(); round++) {
            for (Car car : setupData.cars()) {
                car.tryMove(forwardPolicy);
                recordCarDistance(roundSnapShot, car);
            }
            roundSnapShot.append(ROUND_HISTORY_NEW_LINE.getMessage());
        }

        return roundSnapShot.toString();
    }

    private void recordCarDistance(StringBuilder roundSnapShot, Car car) {
        roundSnapShot.append(car.getName());
        roundSnapShot.append(ROUND_HISTORY_NEXT_TO_CAR_NAME.getMessage());
        roundSnapShot.append(car.getDistanceDisplay());
        roundSnapShot.append(ROUND_HISTORY_NEW_LINE.getMessage());
    }
}
