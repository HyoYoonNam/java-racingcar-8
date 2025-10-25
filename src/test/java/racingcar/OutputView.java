package racingcar;

import java.util.List;

public class OutputView {

    private static final String POSITION_FORMAT = "-";

    private OutputView() {
    }

    public static void printCarPositions(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + POSITION_FORMAT.repeat(car.getPosition()));
        }
    }
}
