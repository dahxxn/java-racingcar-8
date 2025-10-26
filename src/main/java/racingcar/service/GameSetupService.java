package racingcar.service;

import java.util.List;
import racingcar.model.Car;
import racingcar.record.InputData;
import racingcar.record.SetupData;
import racingcar.util.Parser;
import racingcar.util.Validator;

public class GameSetupService {
    public SetupData prepare(InputData inputData) {
        SetupData setupData = parseInputData(inputData);
        validateSetupData(setupData);
        return setupData;
    }

    private SetupData parseInputData(InputData inputData) {
        List<Car> cars = Parser.getCarList(inputData.carsData());
        int rounds = Parser.getRoundNumber(inputData.roundData());
        return new SetupData(cars, rounds);
    }

    private void validateSetupData(SetupData setupData) {
        validateCars(setupData.cars());
        Validator.validateRoundCount(setupData.rounds());
    }

    private void validateCars(List<Car> cars) {
        Validator.validateCarCount(cars);
        for (Car car : cars) {
            Validator.validateCarName(car.getName());
        }
        Validator.validateNoDuplicateCarNames(cars);
    }
}
