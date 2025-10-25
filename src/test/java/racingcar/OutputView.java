package racingcar;

import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printCarPositions(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + "-".repeat(car.getPosition()));
        }
    }
}
