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
    @DisplayName("공동 우승 테스트: 라운드 수만큼 누적 전진하여 공동 우승자가 된다")
    void 공동_우승_테스트() {
        //given
        GamePlayService gamePlayService = new GamePlayService(new AlwaysMovePolicy());
        SetupData setupData = new SetupData(List.of(new Car("pobi"), new Car("woni")), 3);

        //when
        RaceData result = gamePlayService.play(setupData);

        //then
        assertThat(result.roundSnapShot())
                .contains("pobi : -")
                .contains("pobi : --")
                .contains("pobi : ---")
                .contains("woni : -")
                .contains("woni : --")
                .contains("woni : ---");

        assertThat(result.finalWinners()).isEqualTo("pobi,woni");
    }


    @Test
    @DisplayName("번갈아 이동 정책: 첫 호출만 이동하게 함으로써 첫 번째 차만 우승자가 된다")
    void 단일_우승_테스트() {
        //given
        GamePlayService gamePlayService = new GamePlayService(new SwitchingMovePolicy());
        SetupData setupData = new SetupData(List.of(new Car("pobi"), new Car("woni")), 3);

        //when
        RaceData result = gamePlayService.play(setupData);

        //then
        assertThat(result.roundSnapShot())
                .contains("pobi : -")
                .contains("pobi : --")
                .contains("pobi : ---");
        assertThat(result.roundSnapShot())
                .doesNotContain("woni : -");
        assertThat(result.finalWinners()).isEqualTo("pobi");
    }

    static class AlwaysMovePolicy extends ForwardPolicy {
        @Override
        public boolean canMove() {
            return true;
        }
    }

    static class NeverMovePolicy extends ForwardPolicy {
        @Override
        public boolean canMove() {
            return false;
        }
    }

    static class SwitchingMovePolicy extends ForwardPolicy {
        private int call = 0;

        @Override
        public boolean canMove() {
            return (call++ % 2) == 0;
        }
    }
}
