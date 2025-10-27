package racingcar.domain;

import java.util.regex.Pattern;

import racingcar.exception.ErrorMessage;

public class Car {

    private static final int MOVE_FORWARD_THRESHOLD = 4;
    private static final int MAX_NAME_LENGTH = 5;

    private int position = 0;
    private final String name;

    public Car(String name) {
        NameValidator.validate(name);
        this.name = name;
    }

    public void move(int number) {
        if (number < MOVE_FORWARD_THRESHOLD) {
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
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_INVALID.build(name));
            }
        }

        private static void validateNameLength(String name) {
            if (name.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_GREATER_THAN_MAXIMUM.build(name));
            }
        }
    }
}
