package racingcar.view;

import static racingcar.constant.GameGuide.FINAL_WINNERS;
import static racingcar.constant.GameGuide.ROUND_HISTORY_HEAD;

import racingcar.record.RaceData;

public class OutputView {
    public void showRaceData(RaceData raceData) {
        showRoundData(raceData.roundSnapshot());
        showFinalWinners(raceData.finalWinners());
    }

    private void showRoundData(String roundSnapshot) {
        System.out.println();
        System.out.println(ROUND_HISTORY_HEAD.getMessage());
        System.out.println(roundSnapshot);
    }

    private void showFinalWinners(String finalWinners) {
        System.out.print(FINAL_WINNERS.getMessage());
        System.out.println(finalWinners);
    }
}
