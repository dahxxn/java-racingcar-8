package racingcar.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.GameGuide;

public class InputViewTest {
    private InputView inputView;
    private InputStream inputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
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
    @DisplayName("자동차 이름 목록 입력받기 테스트")
    void 자동차_이름_목록_입력받기() {
        //given
        String input = "pobi,woni,jun\n";
        mockReadLine(input);
        ByteArrayOutputStream output = mockOutput();

        //when
        String carsData = inputView.readCarsData();

        //then
        assertAll(
                () -> assertThat(carsData).isEqualTo("pobi,woni,jun"),
                () -> assertThat(output.toString()).contains(GameGuide.INPUT_CAR_NAMES.getMessage())
        );
    }

    @Test
    @DisplayName("시도할 횟수 입력받기 테스트")
    void 시도할_횟수_입력받기() {
        //given
        String input = "5\n";
        mockReadLine(input);
        ByteArrayOutputStream output = mockOutput();

        //when
        String roundData = inputView.readRoundData();

        //then
        assertAll(
                () -> assertThat(roundData).isEqualTo("5"),
                () -> assertThat(output.toString()).contains(GameGuide.INPUT_ROUND.getMessage())
        );
    }

    private void mockReadLine(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private ByteArrayOutputStream mockOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }


}
