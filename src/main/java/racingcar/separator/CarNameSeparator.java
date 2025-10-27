package racingcar.separator;

import java.util.Arrays;
import java.util.List;

public class CarNameSeparator {

    public static final String DELIMITER = ",";

    private CarNameSeparator() {
    }

    public static List<String> separate(String carNamesString) {
        return Arrays.stream(carNamesString.split(DELIMITER))
                .map(String::strip)
                .toList();
    }
}
