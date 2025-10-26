package racingcar.policy;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class ForwardPolicy {
    private static final int MIN_RANDOM_VALUE = 0;
    private static final int MAX_RANDOM_VALUE = 9;
    private static final int FORWARD_THRESHOLD = 4;

    public boolean canMove() {
        return pickNumberInRange(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE) >= FORWARD_THRESHOLD;
    }
}