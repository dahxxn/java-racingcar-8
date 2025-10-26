package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.policy.ForwardPolicy;
import racingcar.record.RaceData;
import racingcar.record.SetupData;

class GamePlayServiceTest {

    @Test
    @DisplayName("게임 진행 테스트: 모든 자동차가 동일하게 이동할 때 공동 우승")
    void 게임_진행_공동_우승() {
        //given
        GamePlayService gamePlayService = new GamePlayService(new AlwaysMovePolicy());
        SetupData setupData = new SetupData(List.of(new Car("pobi"), new Car("woni")), 3);

        //when
        RaceData raceData = gamePlayService.play(setupData);

        //then
        assertThat(raceData.roundSnapshot())
                .contains("pobi : -")
                .contains("pobi : --")
                .contains("pobi : ---")
                .contains("woni : -")
                .contains("woni : --")
                .contains("woni : ---");
        assertThat(raceData.finalWinners()).isEqualTo("pobi, woni");
    }

    @Test
    @DisplayName("게임 진행 테스트: 한 자동차만 이동할 때 단일 우승")
    void 게임_진행_단일_우승() {
        //given
        GamePlayService gamePlayService = new GamePlayService(new SwitchingMovePolicy());
        SetupData setupData = new SetupData(List.of(new Car("pobi"), new Car("woni")), 3);

        //when
        RaceData raceData = gamePlayService.play(setupData);

        //then
        assertThat(raceData.roundSnapshot())
                .contains("pobi : -")
                .contains("pobi : --")
                .contains("pobi : ---")
                .doesNotContain("woni : -");
        assertThat(raceData.finalWinners()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("게임 진행 테스트: 모든 자동차가 이동하지 않을 때")
    void 게임_진행_모두_정지() {
        //given
        GamePlayService gamePlayService = new GamePlayService(new NeverMovePolicy());
        SetupData setupData = new SetupData(List.of(new Car("pobi"), new Car("woni")), 3);

        //when
        RaceData raceData = gamePlayService.play(setupData);

        //then
        assertThat(raceData.roundSnapshot())
                .contains("pobi : ")
                .contains("woni : ")
                .doesNotContain("-");
        assertThat(raceData.finalWinners()).isEqualTo("pobi, woni");
    }

    private static class AlwaysMovePolicy extends ForwardPolicy {
        @Override
        public boolean canMove() {
            return true;
        }
    }

    private static class NeverMovePolicy extends ForwardPolicy {
        @Override
        public boolean canMove() {
            return false;
        }
    }

    private static class SwitchingMovePolicy extends ForwardPolicy {
        private int callCount = 0;

        @Override
        public boolean canMove() {
            return (callCount++ % 2) == 0;
        }
    }
}