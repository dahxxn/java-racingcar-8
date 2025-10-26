package racingcar.controller;

import racingcar.record.InputData;
import racingcar.record.RaceData;
import racingcar.record.SetupData;
import racingcar.service.GamePlayService;
import racingcar.service.GameSetupService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    private final InputView inputView;
    private final GameSetupService gameSetupService;
    private final GamePlayService gamePlayService;
    private final OutputView outputView;

    public RacingCarController(InputView inputView, GameSetupService gameSetupService,
                               GamePlayService gamePlayService, OutputView outputView) {
        this.inputView = inputView;
        this.gameSetupService = gameSetupService;
        this.gamePlayService = gamePlayService;
        this.outputView = outputView;
    }

    public void run() {
        InputData inputData = inputView.readInputData();
        SetupData setupData = gameSetupService.prepare(inputData);
        RaceData raceData = gamePlayService.play(setupData);
        outputView.showRaceData(raceData);
    }

}
