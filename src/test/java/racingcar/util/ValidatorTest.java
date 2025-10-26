package racingcar.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.error.ErrorMessage.CAR_COUNT_ERROR;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_DUPLICATE;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_EMPTY;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_LONG;
import static racingcar.error.ErrorMessage.ROUND_COUNT_ERROR;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;

public class ValidatorTest {

    @Test
    @DisplayName("자동차 수 검증 테스트: 2대 이상일 때 통과")
    void 자동차_수_검증_정상() {
        //given
        List<Car> cars = List.of(
                new Car("pobi"),
                new Car("woni"),
                new Car("jun")
        );

        //when & then
        assertThatCode(() -> Validator.validateCarCount(cars))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 수 검증 예외 테스트: 1대일 때 예외 발생")
    void 자동차_수_검증_예외_1개() {
        //given
        List<Car> cars = List.of(
                new Car("pobi")
        );

        //when & then
        assertThatThrownBy(() -> Validator.validateCarCount(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("자동차 수 검증 예외 테스트: 0대일 때 예외 발생")
    void 자동차_수_검증_예외_0개() {
        //given
        List<Car> cars = new ArrayList<>();

        //when & then
        assertThatThrownBy(() -> Validator.validateCarCount(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("자동차 이름 검증 테스트: 정상적인 이름일 때 통과")
    void 자동차_이름_검증_정상() {
        //given
        String carName = "pobi";

        //when & then
        assertThatCode(() -> Validator.validateCarName(carName))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 이름 검증 예외 테스트: 공백일 때 예외 발생")
    void 자동차_이름_검증_예외_공백() {
        //given
        String carName = "";

        //when & then
        assertThatThrownBy(() -> Validator.validateCarName(carName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_EMPTY.getMessage());
    }

    @Test
    @DisplayName("자동차 이름 검증 예외 테스트: 5자 초과일 때 예외 발생")
    void 자동차_이름_검증_예외_길이초과() {
        //given
        String carName = "verylongname";

        //when & then
        assertThatThrownBy(() -> Validator.validateCarName(carName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_LONG.getMessage());
    }

    @Test
    @DisplayName("자동차 이름 중복 검증 테스트: 중복이 없을 때 통과")
    void 자동차_이름_중복_검증_정상() {
        //given
        List<Car> cars = List.of(
                new Car("pobi"),
                new Car("woni"),
                new Car("jun")
        );

        //when & then
        assertThatCode(() -> Validator.validateNoDuplicateCarNames(cars))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 이름 중복 검증 예외 테스트: 중복일 때 예외 발생")
    void 자동차_이름_중복_검증_예외() {
        //given
        List<Car> cars = List.of(
                new Car("pobi"),
                new Car("woni"),
                new Car("pobi")
        );

        //when & then
        assertThatThrownBy(() -> Validator.validateNoDuplicateCarNames(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("자동차 이름 중복 검증 테스트: 대소문자가 다를 때 통과")
    void 자동차_이름_중복_검증_정상_대소문자() {
        //given
        List<Car> cars = List.of(
                new Car("pobi"),
                new Car("Pobi")
        );

        //when & then
        assertThatCode(() -> Validator.validateNoDuplicateCarNames(cars))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("라운드 수 검증 테스트: 정상적인 라운드 수일 때 통과")
    void 라운드_수_검증_정상() {
        //given
        int roundCount = 5;

        //when & then
        assertThatCode(() -> Validator.validateRoundCount(roundCount))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("라운드 수 검증 예외 테스트: 0일 때 예외 발생")
    void 라운드_수_검증_예외_0회() {
        //given
        int roundCount = 0;

        //when & then
        assertThatThrownBy(() -> Validator.validateRoundCount(roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("라운드 수 검증 예외 테스트: 음수일 때 예외 발생")
    void 라운드_수_검증_예외_음수() {
        //given
        int roundCount = -3;

        //when & then
        assertThatThrownBy(() -> Validator.validateRoundCount(roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage());
    }
}