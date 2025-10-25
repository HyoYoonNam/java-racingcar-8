package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// System.out.println() test reference:
// https://www.geeksforgeeks.org/advance-java/unit-testing-of-system-out-println-with-junit/
public class OutputViewTest {

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

    @Test
    @DisplayName("자동차들의 위치를 '-'로 포매팅해서 출력한다")
    void printCarPositions_outputContainsFormattedPosition_carMoved() {
        List<Car> cars = List.of(new Car("rude"), new Car("vico"));
        for (Car car : cars) {
            car.move(5);
        }

        OutputView.printCarPositions(cars);

        assertThat(outputStream.toString()).contains("rude : -", "vico : -");
    }
}
