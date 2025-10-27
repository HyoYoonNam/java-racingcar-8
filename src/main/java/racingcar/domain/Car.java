package racingcar.domain;

import static racingcar.exception.ErrorMessage.CAR_NAME_INVALID;
import static racingcar.exception.ErrorMessage.CAR_NAME_LENGTH_GREATER_THAN_MAXIMUM;

import java.util.regex.Pattern;

public class Car {

    private int position = 0;
    private final String name;

    public Car(String name) {
        NameValidator.validate(name);
        this.name = name;
    }

    public void move(int number) {
        if (number < 4) {
            // do nothing
            return;
        }

        this.position++;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    private static class NameValidator {

        private static final String INVALID_NAME_REGEX = "[^a-zA-Z0-9_ ]|[_ ]{2,}";
        private static final Pattern INVALID_NAME_PATTERN = Pattern.compile(INVALID_NAME_REGEX);

        private static void validate(String name) {
            validateNamePattern(name);
            validateNameLength(name);
        }

        private static void validateNamePattern(String name) {
            if (INVALID_NAME_PATTERN.matcher(name).find()) {
                throw new IllegalArgumentException(CAR_NAME_INVALID.build(name));
            }
        }

        private static void validateNameLength(String name) {
            if (name.length() > 5) {
                throw new IllegalArgumentException(CAR_NAME_LENGTH_GREATER_THAN_MAXIMUM.build(name));
            }
        }
    }
}
