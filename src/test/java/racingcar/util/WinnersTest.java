package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.policy.ForwardPolicy;

class WinnersTest {
    @Test
    @DisplayName("우승자 선정 테스트: 공동 우승일 때 쉼표로 연결하여 반환")
    void 우승자_선정_공동_우승() {
        //given
        Car pobi = carByMoves("pobi", 3);
        Car woni = carByMoves("woni", 1);
        Car jun = carByMoves("jun", 3);

        //when
        String winners = Winners.pickWinners(List.of(pobi, woni, jun));

        //then
        assertThat(winners).isEqualTo("pobi, jun");
    }

    @Test
    @DisplayName("우승자 선정 테스트: 단일 우승일 때 한 명만 반환")
    void 우승자_선정_단일_우승() {
        //given
        Car pobi = carByMoves("pobi", 2);
        Car woni = carByMoves("woni", 5);

        //when
        String winners = Winners.pickWinners(List.of(pobi, woni));

        //then
        assertThat(winners).isEqualTo("woni");
    }

    @Test
    @DisplayName("우승자 선정 테스트: 모든 자동차가 동일 거리일 때 전체 우승")
    void 우승자_선정_전체_공동_우승() {
        //given
        Car pobi = carByMoves("pobi", 2);
        Car woni = carByMoves("woni", 2);
        Car jun = carByMoves("jun", 2);

        //when
        String winners = Winners.pickWinners(List.of(pobi, woni, jun));

        //then
        assertThat(winners).isEqualTo("pobi, woni, jun");
    }

    @Test
    @DisplayName("우승자 선정 테스트: 모든 자동차가 이동하지 않았을 때")
    void 우승자_선정_거리_0() {
        //given
        Car pobi = new Car("pobi");
        Car woni = new Car("woni");

        //when
        String winners = Winners.pickWinners(List.of(pobi, woni));

        //then
        assertThat(winners).isEqualTo("pobi, woni");
    }

    private Car carByMoves(String name, int moves) {
        Car car = new Car(name);
        AlwaysMovePolicy policy = new AlwaysMovePolicy();
        for (int i = 0; i < moves; i++) {
            car.tryMove(policy);
        }
        return car;
    }

    private static class AlwaysMovePolicy extends ForwardPolicy {
        @Override
        public boolean canMove() {
            return true;
        }
    }
}