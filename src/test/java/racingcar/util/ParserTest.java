package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;

public class ParserTest {
    @Test
    @DisplayName("자동차 목록 파싱 테스트: 정상적으로 파싱될 때 통과")
    void 자동차_목록_파싱_정상() {
        //given
        String carsData = "pobi,woni,jun";

        //when
        List<Car> cars = Parser.getCarList(carsData);

        //then
        assertThat(cars)
                .extracting(Car::getName)
                .containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("라운드 수 변환 테스트: 정상적으로 변환될 때 통과")
    void 라운드_수_변환_정상() {
        //given
        String roundData = "5";

        //when
        int round = Parser.getRoundNumber(roundData);

        //then
        assertThat(round).isEqualTo(5);
    }

    @Test
    @DisplayName("라운드 수 변환 예외 테스트: 숫자가 아닐 때 예외 발생")
    void 라운드_수_변환_예외_숫자_아님() {
        //given
        String roundData = "다섯";

        //when & then
        assertThatThrownBy(() -> Parser.getRoundNumber(roundData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());
    }
}