package racingcar;

import java.util.Arrays;
import java.util.List;

public class CarNameSeparator {

    public static final String DELIMITER = ",";

    public List<String> separate(String carNamesString) {
        return Arrays.stream(carNamesString.split(DELIMITER))
                .map(String::strip)
                .toList();
    }
}
