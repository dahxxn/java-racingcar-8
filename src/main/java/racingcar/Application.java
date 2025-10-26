package racingcar;

import racingcar.controller.RacingCarController;
import racingcar.policy.ForwardPolicy;
import racingcar.service.GamePlayService;
import racingcar.service.GameSetupService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        GameSetupService gameSetupService = new GameSetupService();
        GamePlayService gamePlayService = new GamePlayService(new ForwardPolicy());
        OutputView outputView = new OutputView();

        RacingCarController racingCarController = new RacingCarController(inputView, gameSetupService, gamePlayService,
                outputView);
        racingCarController.run();
    }
}
