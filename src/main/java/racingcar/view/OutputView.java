package racingcar.view;

import static racingcar.constant.GameGuide.FINAL_WINNERS;
import static racingcar.constant.GameGuide.ROUND_HISTORY_HEAD;

import racingcar.record.RaceData;

public class OutputView {
    public void showRaceData(RaceData raceData) {
        showRoundData(raceData.roundSnapShot());
        showFinalWinners(raceData.finalWinners());
    }

    private void showRoundData(String roundSnapShot) {
        System.out.println(ROUND_HISTORY_HEAD.getMessage());
        System.out.println(roundSnapShot);
    }

    private void showFinalWinners(String finalWinners) {
        System.out.printf(FINAL_WINNERS.getMessage());
        System.out.println(finalWinners);
    }
}
