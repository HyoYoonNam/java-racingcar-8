package racingcar;

import java.util.regex.Pattern;

public class Car {

    public Car(String name) {
        NameValidator.validate(name);
    }

    private static class NameValidator {

        private static final String invalidNameRegex = "[^a-zA-Z0-9_ ]|[_ ]{2,}";
        private static final Pattern invalidNamePattern = Pattern.compile(invalidNameRegex);

        private static void validate(String name) {
            validateNamePattern(name);
            validateNameLength(name);
        }

        private static void validateNamePattern(String name) {
            if (invalidNamePattern.matcher(name).find()) {
                throw new IllegalArgumentException("사용할 수 없는 이름입니다: " + name);
            }
        }

        private static void validateNameLength(String name) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("이름은 5자를 넘을 수 없습니다: " + name);
            }
        }
    }
}
