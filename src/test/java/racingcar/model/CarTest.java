package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.policy.ForwardPolicy;

class CarTest {

    @Test
    @DisplayName("자동차 생성 테스트: 이름과 초기 거리 0으로 생성될 때 통과")
    void 자동차_생성_정상() {
        //given
        String name = "pobi";

        //when
        Car car = new Car(name);

        //then
        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차 이동 테스트: 전진 조건을 만족할 때 이동")
    void 자동차_이동_전진() {
        //given
        Car car = new Car("pobi");
        ForwardPolicy policy = new AlwaysMovePolicy();

        //when
        car.tryMove(policy);

        //then
        assertThat(car.getDistance()).isEqualTo(1);
    }

    @Test
    @DisplayName("자동차 이동 테스트: 전진 조건을 만족하지 않을 때 정지")
    void 자동차_이동_정지() {
        //given
        Car car = new Car("pobi");
        ForwardPolicy policy = new NeverMovePolicy();

        //when
        car.tryMove(policy);

        //then
        assertThat(car.getDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차 이동 테스트: 여러 번 이동할 때 거리 누적")
    void 자동차_이동_여러번() {
        //given
        Car car = new Car("pobi");
        ForwardPolicy policy = new AlwaysMovePolicy();

        //when
        for (int i = 0; i < 3; i++) {
            car.tryMove(policy);
        }

        //then
        assertThat(car.getDistance()).isEqualTo(3);
    }

    @Test
    @DisplayName("자동차 거리 표시 테스트: 거리가 0일 때 빈 문자열 반환")
    void 자동차_거리_표시_0() {
        //given
        Car car = new Car("pobi");

        //when
        String display = car.getDistanceDisplay();

        //then
        assertThat(display).isEmpty();
    }

    @Test
    @DisplayName("자동차 거리 표시 테스트: 거리만큼 하이픈 반환")
    void 자동차_거리_표시_정상() {
        //given
        Car car = new Car("pobi");
        ForwardPolicy policy = new AlwaysMovePolicy();

        //when
        for (int i = 0; i < 3; i++) {
            car.tryMove(policy);
        }
        String display = car.getDistanceDisplay();

        //then
        assertThat(display).isEqualTo("---");
    }

    @Test
    @DisplayName("자동차 이름 조회 테스트: 생성 시 저장한 이름 반환")
    void 자동차_이름_조회() {
        //given
        String name = "pobi";
        Car car = new Car(name);

        //when
        String result = car.getName();

        //then
        assertThat(result).isEqualTo("pobi");
    }

    @Test
    @DisplayName("자동차 거리 조회 테스트: 현재 거리 값 반환")
    void 자동차_거리_조회() {
        //given
        Car car = new Car("pobi");
        ForwardPolicy policy = new AlwaysMovePolicy();
        for (int i = 0; i < 3; i++) {
            car.tryMove(policy);
        }

        //when
        int distance = car.getDistance();

        //then
        assertThat(distance).isEqualTo(3);
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
}