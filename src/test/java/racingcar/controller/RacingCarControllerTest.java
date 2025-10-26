package racingcar.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_DUPLICATE;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_EMPTY;
import static racingcar.error.ErrorMessage.CAR_NAME_ERROR_LONG;
import static racingcar.error.ErrorMessage.ROUND_COUNT_ERROR;
import static racingcar.error.ErrorMessage.ROUND_FORMAT_ERROR_NOT_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.policy.ForwardPolicy;
import racingcar.service.GamePlayService;
import racingcar.service.GameSetupService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

class RacingCarControllerTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private RacingCarController racingCarController;
    private InputStream inputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        InputView inputView = new InputView();
        GameSetupService gameSetupService = new GameSetupService();
        GamePlayService gamePlayService = new GamePlayService(new ForwardPolicy());
        OutputView outputView = new OutputView();

        racingCarController = new RacingCarController(inputView, gameSetupService, gamePlayService, outputView);

        inputStream = System.in;
        printStream = System.out;
    }

    @AfterEach
    void cleanUp() {
        System.setIn(inputStream);
        System.setOut(printStream);
        Console.close();
    }

    @Test
    @DisplayName("정상적인 게임 진행 테스트: 단일 라운드의 정상 게임 진행")
    void 정상_게임_진행() {
        assertRandomNumberInRangeTest(
                () -> {
                    //given
                    String input = "pobi,woni\n1\n";
                    mockInput(input);
                    ByteArrayOutputStream output = mockOutput();

                    //when
                    racingCarController.run();
                    String result = output.toString();

                    //then
                    assertThat(result).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("여러 라운드 게임 진행 테스트: 여러 라운드의 정상 게임 진행")
    void 여러_라운드_게임_진행() {
        assertRandomNumberInRangeTest(
                () -> {
                    //given
                    String input = "pobi,woni,jun\n3\n";
                    mockInput(input);
                    ByteArrayOutputStream output = mockOutput();

                    //when
                    racingCarController.run();
                    String result = output.toString();

                    //then
                    assertThat(result).contains("pobi :", "woni :", "jun :", "최종 우승자");
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP,
                MOVING_FORWARD, STOP, STOP,
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("잘못된 자동차 이름으로 예외 발생 테스트: 5자 이상 자동차 이름으로 예외 발생")
    void 잘못된_자동차_이름_예외_테스트_5자초과() {
        //given
        String input = "pobi,toolongname\n1\n";
        mockInput(input);
        mockOutput();

        //when & then
        assertThatThrownBy(() -> racingCarController.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_LONG.getMessage())
        ;
    }

    @Test
    @DisplayName("잘못된 자동차 이름으로 예외 발생 테스트: 자동차 이름이 공백으로 예외 발생")
    void 잘못된_자동차_이름_예외_테스트_이름공백() {
        //given
        String input = "pobi,,woni\n1\n";
        mockInput(input);
        mockOutput();

        //when & then
        assertThatThrownBy(() -> racingCarController.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_EMPTY.getMessage())
        ;
    }

    @Test
    @DisplayName("잘못된 자동차 이름으로 예외 발생 테스트: 자동차 이름이 중복으로 예외 발생")
    void 잘못된_자동차_이름_예외_테스트_중복() {
        //given
        String input = "pobi,woni,pobi\n1\n";
        mockInput(input);
        mockOutput();

        //when & then
        assertThatThrownBy(() -> racingCarController.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CAR_NAME_ERROR_DUPLICATE.getMessage())
        ;
    }

    @Test
    @DisplayName("잘못된 라운드 입력으로 예외 발생 테스트: 라운드 수가 0으로 예외 발생")
    void 잘못된_라운드_입력_예외_테스트_0() {
        //given
        String input = "pobi,woni\n0\n";
        mockInput(input);
        mockOutput();

        //when & then
        assertThatThrownBy(() -> racingCarController.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_COUNT_ERROR.getMessage())
        ;
    }

    @Test
    @DisplayName("잘못된 라운드 입력으로 예외 발생 테스트: 라운드 수가 숫자가 아님으로 예외 발생")
    void 잘못된_라운드_입력_예외_테스트_숫자_아님() {
        //given
        String input = "pobi,woni\nfive\n";
        mockInput(input);
        mockOutput();

        //when & then
        assertThatThrownBy(() -> racingCarController.run())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ROUND_FORMAT_ERROR_NOT_NUMBER.getMessage())
        ;
    }

    private void mockInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private ByteArrayOutputStream mockOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }
}