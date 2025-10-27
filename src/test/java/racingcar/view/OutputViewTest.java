package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import racingcar.domain.Car;

// System.out.println() test reference:
// https://www.geeksforgeeks.org/advance-java/unit-testing-of-system-out-println-with-junit/
public class OutputViewTest {

    private static final String POSITION_FORMAT = "-";
    private static final String PRINT_WINNERS_PREFIX = "최종 우승자 : ";
    private static final int MOVE_FORWARD_THRESHOLD = 4;

    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @ParameterizedTest(name = "[{index}] 숫자 0부터 9까지의 모든 이동 -> {1}")
    @MethodSource("provideAllMoveScenarios")
    @DisplayName("자동차들의 위치를 '-'로 포매팅해서 출력한다")
    void printCarPositions_outputContainsFormattedPosition_carMoved(Map<Car, Integer> carNumberMap,
                                                                    String expectedOutput) {
        carNumberMap.forEach((car, number) -> car.move(number));

        OutputView.printCarPositions(carNumberMap.keySet().stream().toList());

        assertThat(outputStream.toString()).contains(expectedOutput);
    }

    @ParameterizedTest(name = "[{index}] {0} -> \"{1}\"")
    @MethodSource("provideWinnersAndExpectedOutput")
    @DisplayName("최종 우승자(들) 이름을 출력한다")
    void printWinners_outputContainsExpectedOutput(List<String> winners, String expectedOutput) {
        OutputView.printWinners(winners);

        assertThat(outputStream.toString()).contains(expectedOutput);
    }

    private static Stream<Arguments> provideAllMoveScenarios() {
        Map<Car, Integer> carNumberMap = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= 9; i++) {
            String name = "car" + i;
            carNumberMap.put(new Car(name), i);

            sb.append(name).append(" : ");
            if (i >= MOVE_FORWARD_THRESHOLD) {
                sb.append(POSITION_FORMAT);
            }
            sb.append("\n");
        }

        return Stream.of(
                Arguments.of(carNumberMap, sb.toString())
        );
    }

    private static Stream<Arguments> provideWinnersAndExpectedOutput() {
        return Stream.of(
                Arguments.of(List.of("pobi"), PRINT_WINNERS_PREFIX + "pobi"),
                Arguments.of(List.of("pobi", "jun"), PRINT_WINNERS_PREFIX + "pobi, jun")
        );
    }
}
