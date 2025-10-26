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
    @DisplayName("경주 결과 출력 테스트: 라운드 기록과 우승자를 정상 출력")
    void 경주_결과_출력_정상() {
        //given
        String roundSnapshot = "pobi : --\nwoni : ---\njun : -";
        String finalWinners = "woni";
        RaceData raceData = new RaceData(roundSnapshot, finalWinners);
        ByteArrayOutputStream output = mockOutput();

        //when
        outputView.showRaceData(raceData);
        String result = output.toString();

        //then
        assertAll(
                () -> assertThat(result).contains(GameGuide.ROUND_HISTORY_HEAD.getMessage()),
                () -> assertThat(result).contains(roundSnapshot),
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