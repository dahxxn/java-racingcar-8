package racingcar.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;

public class ParserTest {

    @Test
    @DisplayName("자동차 목록으로 파싱하기 테스트")
    void 자동차_목록으로_파싱_테스트() {
        //given
        String carsData = "pobi,woni,jun\n";

        //when
        List<Car> cars = Parser.getCarList(carsData);

        //then
        List<Car> expectedCars = List.of(
                new Car("pobi"),
                new Car("woni"),
                new Car("jun")
        );
        assertThat(cars).usingRecursiveComparison().isEqualTo(expectedCars);
    }

    @Test
    @DisplayName("시도할 횟수를 정수로 변환하기 테스트")
    void 시도할_횟수_정수_변환_테스트() {
        //given
        String roundData = "5\n";

        //when
        int round = Parser.getRoundNumber(roundData);

        //then
        int expectedRound = 5;
        assertThat(round).isEqualTo(expectedRound);
    }

    @Test
    @DisplayName("시도할 횟수가 숫자 형식이 아닐때 변환하기 테스트")
    void 시도할_횟수_정수_변환_테스트_에러() {
        //given
        String roundData = "다섯\n";

        //when & then
        assertThatThrownBy(() -> Parser.getRoundNumber(roundData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());

    }
}
