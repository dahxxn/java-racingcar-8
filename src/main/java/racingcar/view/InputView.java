package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.constant.GameMessage;
import racingcar.record.InputData;

public class InputView {

    public InputData readInputData() {
        String carsData = readCarsData();
        String roundData = readRoundData();
        return new InputData(carsData, roundData);
    }

    private String readCarsData() {
        return readRawData(GameMessage.INPUT_CAR_NAMES.getMessage());
    }

    private String readRoundData() {
        return readRawData(GameMessage.INPUT_ROUND.getMessage());
    }

    private String readRawData(String inputMessage) {
        System.out.println(inputMessage);
        return Console.readLine();
    }


}
