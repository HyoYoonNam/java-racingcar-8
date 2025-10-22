package racingcar;

import java.util.Arrays;
import java.util.List;

public class CarNameSeparator {

    public static final String DELIMITER = ",";

    public List<String> separate(String carname) {
        return Arrays.stream(carname.split(DELIMITER))
                .map(String::strip)
                .toList();
    }
}
