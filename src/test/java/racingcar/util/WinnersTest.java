package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.policy.ForwardPolicy;

class WinnersTest {

    @Test
    @DisplayName("공동 우승 테스트: 최대 거리 동일 시 공동 우승자를 쉼표로 연결해 반환한다")
    void 공동_우승_테스트() {
        //given
        Car a = carByMoves("pobi", 3);
        Car b = carByMoves("woni", 1);
        Car c = carByMoves("jun", 3);

        //when
        String winners = Winners.pickWinners(List.of(a, b, c));

        //then
        assertThat(winners).isEqualTo("pobi,jun");
    }

    @Test
    @DisplayName("단일 우승 테스트: 단일 최대 거리의 자동차가 단일 우승자가 된다")
    void 단일_우승_테스트() {
        //given
        Car a = carByMoves("pobi", 2);
        Car b = carByMoves("woni", 5);

        //when
        String winners = Winners.pickWinners(List.of(a, b));

        //then
        assertThat(winners).isEqualTo("woni");
    }


    static class AlwaysMovePolicy extends ForwardPolicy {
        @Override
        public boolean canMove() {
            return true;
        }
    }

    private Car carByMoves(String name, int moves) {
        Car c = new Car(name);
        var policy = new AlwaysMovePolicy();
        for (int i = 0; i < moves; i++) {
            c.tryMove(policy);
        }
        return c;
    }
}
