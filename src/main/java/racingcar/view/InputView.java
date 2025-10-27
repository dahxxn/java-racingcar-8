package racingcar.view;

import static racingcar.constant.GameMessage.INPUT_CAR_NAMES;
import static racingcar.constant.GameMessage.INPUT_ROUND;

import camp.nextstep.edu.missionutils.Console;
import racingcar.record.InputData;

public class InputView {
    public InputData readInputData() {
        String carsData = readCarsData();
        String roundData = readRoundData();
        return new InputData(carsData, roundData);
    }

    private String readCarsData() {
        return readRawData(INPUT_CAR_NAMES.getMessage());
    }

    private String readRoundData() {
        return readRawData(INPUT_ROUND.getMessage());
    }

    private String readRawData(String inputMessage) {
        System.out.println(inputMessage);
        return Console.readLine();
    }
}
