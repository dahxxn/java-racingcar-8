package racingcar.service;

import java.util.List;
import racingcar.model.Car;
import racingcar.record.InputData;
import racingcar.record.SetupData;
import racingcar.util.Parser;
import racingcar.util.Validator;

public class GameSetupService {
    public SetupData prepare(InputData inputData) {
        SetupData parseData = parseInputData(inputData);
        validateAll(parseData);
        return parseData;
    }

    private void validateAll(SetupData data) {
        validateCars(data.cars());
        Validator.validateRoundCount(data.rounds());
    }

    private void validateCars(List<Car> cars) {
        Validator.validateCarCount(cars);
        for (Car c : cars) {
            Validator.validateCarName(c.getName());
        }
        Validator.validateCarNameDuplicate(cars);
    }

    private SetupData parseInputData(InputData inputData) {
        List<Car> cars = Parser.getCarList(inputData.carsData());
        int rounds = Parser.getRoundNumber(inputData.roundData());
        return new SetupData(cars, rounds);
    }

}
