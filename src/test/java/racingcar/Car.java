package racingcar;

import java.util.regex.Pattern;

public class Car {

    private static final String invalidNameRegex = "[^a-zA-Z0-9_ ]|[_ ]{2,}";
    private static final Pattern invalidNamePattern = Pattern.compile(invalidNameRegex);

    public Car(String name) {
        if (invalidNamePattern.matcher(name).find()) {
            throw new IllegalArgumentException("사용할 수 없는 이름입니다: " + name);
        }
    }
}
