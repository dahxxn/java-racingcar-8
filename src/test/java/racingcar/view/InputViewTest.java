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
import racingcar.record.InputData;

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
    @DisplayName("입력 데이터 읽기 테스트")
    void 입력_데이터_읽기_테스트() {
        //given
        String input = "pobi,woni,jun\n5\n";
        mockReadLine(input);
        ByteArrayOutputStream output = mockOutput();

        //when
        InputData inputData = inputView.readInputData();

        //then
        assertAll(
                () -> assertThat(inputData.carsData()).isEqualTo("pobi,woni,jun"),
                () -> assertThat(inputData.roundData()).isEqualTo("5"),
                () -> assertThat(output.toString()).contains(GameGuide.INPUT_CAR_NAMES.getMessage()),
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