package racingcar.numbergenerator;

public class FixedNumberGenerator implements NumberGenerator {

    private final int number;

    public FixedNumberGenerator(final int number) {
        this.number = number;
    }

    public int generate() {
        return this.number;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(number=" + this.number + ")";
    }
}
