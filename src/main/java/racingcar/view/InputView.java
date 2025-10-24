package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.constant.GameGuide;

public class InputView {

    public String readCarsData() {
        return readRawData(GameGuide.INPUT_CAR_NAMES.getMessage());
    }

    private String readRawData(String inputMessage) {
        System.out.println(inputMessage);
        return Console.readLine();
    }
}
