package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.error.ErrorMessage.CAR_COUNT_ERROR;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_DUPLICATE;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_EMPTY;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_LONG;
import static racingcar.error.ErrorMessage.ROUND_COUNT_ERROR;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.record.InputData;
import racingcar.record.SetupData;

class GameSetupServiceTest {

    private final GameSetupService service = new GameSetupService();

    @Test
    @DisplayName("정상 입력 Setup 테스트")
    void 정상_입력() {
        // given
        InputData input = new InputData("pobi,woni,jun", "5");

        // when
        SetupData result = service.prepare(input);

        // then
        assertThat(result.cars())
                .extracting(Car::getName)
                .containsExactly("pobi", "woni", "jun");
        assertThat(result.rounds()).isEqualTo(5);
    }

    @Test
    @DisplayName("비정상 입력 테스트: 자동차 수가 1대이면 예외가 발생한다")
    void 자동차_수_1대_예외() {
        //given
        InputData input = new InputData("pobi", "3");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("비정상 입력 테스트: 자동차 수가 0대이면 예외가 발생한다(빈 문자열)")
    void 자동차_수_0대_예외() {
        //given
        InputData input = new InputData("", "3");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("비정상 입력 테스트: 자동차 이름이 공백이면 예외가 발생한다")
    void 자동차_이름_공백_예외() {
        //given
        InputData input = new InputData("pobi, ,jun", "3");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_EMPTY.getMessage());
    }

    @Test
    @DisplayName("비정상 입력 테스트: 자동차 이름이 5자를 초과하면 예외가 발생한다")
    void 자동차_이름_길이초과_예외() {
        //given
        InputData input = new InputData("pobi,toolong,jun", "3");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_LONG.getMessage());
    }

    @Test
    @DisplayName("비정상 입력 테스트: 자동차 이름이 중복되면 예외가 발생한다")
    void 자동차_이름_중복_예외() {
        //given
        InputData input = new InputData("pobi,woni,pobi", "3");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("비정상 입력 테스트: 라운드 수가 0이면 예외가 발생한다")
    void 라운드_0_예외() {
        //given
        InputData input = new InputData("pobi,woni,jun", "0");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("비정상 입력 테스트: 라운드 수가 음수면 예외가 발생한다")
    void 라운드_음수_예외() {
        //given
        InputData input = new InputData("pobi,woni,jun", "-2");

        //when & then
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("비정상 입력 테스트: 라운드 입력이 숫자 형식이 아니면 예외가 발생한다")
    void 라운드_형식_예외() {
        InputData input = new InputData("pobi,woni,jun", "five");
        assertThatThrownBy(() -> service.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());
    }
}
