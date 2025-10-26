package racingcar.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.GameGuide;
import racingcar.record.RaceData;

public class OutputViewTest {
    private OutputView outputView;
    private InputStream inputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        outputView = new OutputView();
        inputStream = System.in;
        printStream = System.out;
    }

    @AfterEach
    void cleanUp() {
        System.setIn(inputStream);
        System.setOut(printStream);
    }

    @Test
    @DisplayName("경주 결과 출력 테스트")
    void 경주_결과_출력_테스트() {
        //given
        String roundSnapShot = "pobi : --\nwoni : ---\njun : -";
        String finalWinners = "woni";
        RaceData raceData = new RaceData(roundSnapShot, finalWinners);
        ByteArrayOutputStream output = mockOutput();

        //when
        outputView.showRaceData(raceData);

        //then
        String result = output.toString();
        assertAll(
                () -> assertThat(result).contains(GameGuide.ROUND_HISTORY_HEAD.getMessage()),
                () -> assertThat(result).contains(roundSnapShot),
                () -> assertThat(result).contains(GameGuide.FINAL_WINNERS.getMessage()),
                () -> assertThat(result).contains(finalWinners)
        );
    }

    private ByteArrayOutputStream mockOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }
}