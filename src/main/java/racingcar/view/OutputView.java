package racingcar.view;

import java.util.List;
import racingcar.domain.Car;

public class OutputView {

    private static final String POSITION_FORMAT = "-";
    private static final String HEADER = "실행 결과";
    private static final String PRINT_WINNERS_PREFIX = "최종 우승자 : ";

    private OutputView() {
    }

    public static void printCarPositions(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + " : " + POSITION_FORMAT.repeat(car.getPosition()));
        }
        System.out.println();
    }

    public static void printHeader() {
        System.out.println();
        System.out.println(HEADER);
    }

    public static void printWinners(List<String> winners) {
        System.out.println(PRINT_WINNERS_PREFIX + String.join(", ", winners));
    }
}
