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

    private final GameSetupService gameSetupService = new GameSetupService();

    @Test
    @DisplayName("게임 준비 테스트: 정상 입력일 때 통과")
    void 게임_준비_정상() {
        //given
        InputData input = new InputData("pobi,woni,jun", "5");

        //when
        SetupData result = gameSetupService.prepare(input);

        //then
        assertThat(result.cars())
                .extracting(Car::getName)
                .containsExactly("pobi", "woni", "jun");
        assertThat(result.rounds()).isEqualTo(5);
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 자동차가 1대일 때 예외 발생")
    void 게임_준비_예외_자동차_1대() {
        //given
        InputData input = new InputData("pobi", "3");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 자동차가 0대일 때 예외 발생")
    void 게임_준비_예외_자동차_0대() {
        //given
        InputData input = new InputData("", "3");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_EMPTY.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 자동차 이름이 공백일 때 예외 발생")
    void 게임_준비_예외_이름_공백() {
        //given
        InputData input = new InputData("pobi, ,jun", "3");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_EMPTY.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 자동차 이름이 5자 초과일 때 예외 발생")
    void 게임_준비_예외_이름_길이초과() {
        //given
        InputData input = new InputData("pobi,toolong,jun", "3");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_LONG.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 자동차 이름이 중복일 때 예외 발생")
    void 게임_준비_예외_이름_중복() {
        //given
        InputData input = new InputData("pobi,woni,pobi", "3");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 라운드가 0일 때 예외 발생")
    void 게임_준비_예외_라운드_0() {
        //given
        InputData input = new InputData("pobi,woni,jun", "0");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 라운드가 음수일 때 예외 발생")
    void 게임_준비_예외_라운드_음수() {
        //given
        InputData input = new InputData("pobi,woni,jun", "-2");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("게임 준비 예외 테스트: 라운드가 숫자 형식이 아닐 때 예외 발생")
    void 게임_준비_예외_라운드_형식() {
        //given
        InputData input = new InputData("pobi,woni,jun", "five");

        //when & then
        assertThatThrownBy(() -> gameSetupService.prepare(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage());
    }
}