package racingcar.numbergenerator;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomNumberGenerator implements NumberGenerator {

    private final int startInclusive;
    private final int endInclusive;

    public RandomNumberGenerator(final int startInclusive, final int endInclusive) {
        this.startInclusive = startInclusive;
        this.endInclusive = endInclusive;
    }

    public int generate() {
        return Randoms.pickNumberInRange(startInclusive, endInclusive);
    }
}
