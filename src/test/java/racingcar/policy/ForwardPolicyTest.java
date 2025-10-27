package racingcar.policy;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ForwardPolicyTest {
    @Test
    @DisplayName("전진 가능 여부 테스트: 랜덤 값이 4 이상일 때 전진")
    void 전진_가능_4이상() {
        assertRandomNumberInRangeTest(
                () -> {
                    //given
                    ForwardPolicy policy = new ForwardPolicy();

                    //when
                    boolean result = policy.canMove();

                    //then
                    assertThat(result).isTrue();
                },
                4
        );
    }

    @Test
    @DisplayName("전진 가능 여부 테스트: 랜덤 값이 4 미만일 때 정지")
    void 전진_불가능_4미만() {
        assertRandomNumberInRangeTest(
                () -> {
                    //given
                    ForwardPolicy policy = new ForwardPolicy();

                    //when
                    boolean result = policy.canMove();

                    //then
                    assertThat(result).isFalse();
                },
                3
        );
    }

    @Test
    @DisplayName("전진 가능 여부 테스트: 랜덤 값이 9일 때 전진")
    void 전진_가능_9() {
        assertRandomNumberInRangeTest(
                () -> {
                    //given
                    ForwardPolicy policy = new ForwardPolicy();

                    //when
                    boolean result = policy.canMove();

                    //then
                    assertThat(result).isTrue();
                },
                9
        );
    }

    @Test
    @DisplayName("전진 가능 여부 테스트: 랜덤 값이 0일 때 정지")
    void 전진_불가능_0() {
        assertRandomNumberInRangeTest(
                () -> {
                    //given
                    ForwardPolicy policy = new ForwardPolicy();

                    //when
                    boolean result = policy.canMove();

                    //then
                    assertThat(result).isFalse();
                },
                0
        );
    }
}