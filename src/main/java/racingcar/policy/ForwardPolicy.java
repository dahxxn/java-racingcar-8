package racingcar.policy;

import static camp.nextstep.edu.missionutils.Randoms.pickNumberInRange;

public class ForwardPolicy {

    public boolean canMove() {
        return pickNumberInRange(0, 9) >= 4;
    }

}
