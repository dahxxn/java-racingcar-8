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
        String carsData = inputData.carsData();
        List<Car> cars = Parser.getCarList(carsData);

        String roundData = inputData.roundData();
        int rounds = Parser.getRoundNumber(roundData);

        return new SetupData(cars, rounds);
    }

    private void validateSetupData(SetupData setupData) {
        List<Car> cars = setupData.cars();
        validateCars(cars);

        int rounds = setupData.rounds();
        Validator.validateRoundCount(rounds);
    }

    private void validateCars(List<Car> cars) {
        Validator.validateCarCount(cars);

        for (Car car : cars) {
            String carName = car.getName();
            Validator.validateCarName(carName);
        }

        Validator.validateNoDuplicateCarNames(cars);
    }
}
