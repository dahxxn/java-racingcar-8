package racingcar.util;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.error.ErrorMessage.CAR_COUNT_ERROR;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;

public class ValidatorTest {

    @Test
    @DisplayName("자동차 수 검증 테스트")
    void 자동차_수_검증_테스트_정상() {
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
    @DisplayName("자동차 수 검증 예외 테스트: 자동차 수가 1개일때")
    void 자동차_수_검증_예외_테스트_1개일_때() {
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
    @DisplayName("자동차 수 검증 예외 테스트: 자동차 수가 0개일때")
    void 자동차_수_검증_예외_테스트_0개일_때() {
        //given
        List<Car> cars = new ArrayList<>();

        //when & then
        assertThatThrownBy(() -> Validator.validateCarCount(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("자동차 수 검증: null이면 예외가 발생한다")
    void 자동차_수_검증_예외_null() {
        assertThatThrownBy(() -> Validator.validateCarCount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_COUNT_ERROR.getMessage());
    }
}
