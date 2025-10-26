package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RacingGamePrintTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

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

    @ParameterizedTest(name = "[{index}] {2} for {1} rounds -> {3}]")
    @MethodSource("provideGameParametersAndExpectedOutputs")
    @DisplayName("각 차수별 실행 결과를 출력한다")
    void start_printResultEachRound(String carNamesString, int totalRounds, NumberGenerator numberGenerator,
                                    String[] expectedOutputs) {
        RacingGame racingGame = new RacingGame(carNamesString, totalRounds, numberGenerator);

        // when
        racingGame.start();

        // then
        assertThat(outputStream.toString()).contains(expectedOutputs);
    }

    private static Stream<Arguments> provideGameParametersAndExpectedOutputs() {
        return Stream.of(
                // 전진하는 경우
                Arguments.of("pobi, woni", 1, new FixedNumberGenerator(MOVING_FORWARD),
                        new String[]{"pobi : -", "woni : -"}),
                Arguments.of("pobi, woni, rude", 2, new FixedNumberGenerator(MOVING_FORWARD),
                        new String[]{
                                "pobi : -", "woni : -", "rude : -", // 1회 차
                                "pobi : --", "woni : --", "rude : --" // 2회 차
                        }),
                // 멈추는 경우
                Arguments.of("po_bi, wo ni, rude", 1, new FixedNumberGenerator(STOP),
                        new String[]{
                                "po_bi : ", "wo ni : ", "rude : "
                        })
        );
    }
}
