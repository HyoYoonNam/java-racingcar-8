package racingcar.domain;

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

import racingcar.numbergenerator.FixedNumberGenerator;
import racingcar.numbergenerator.NumberGenerator;

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
    @MethodSource("provideGameParametersAndExpectedEachRoundOutputs")
    @DisplayName("각 차수별 실행 결과를 출력한다")
    void start_printResultEachRound(String carNamesString, int totalRounds, NumberGenerator numberGenerator,
                                    String[] expectedOutputs) {
        RacingGame racingGame = new RacingGame(carNamesString, totalRounds, numberGenerator);

        // when
        racingGame.start();

        // then
        assertThat(outputStream.toString()).contains(expectedOutputs);
    }

    @ParameterizedTest(name = "[{index}] {0} -> {3}")
    @MethodSource("provideGameParametersAndExpectedWinners")
    @DisplayName("마지막 차수가 종료되면 우승자를 출력한다")
    void start_printWinnersAfterFinalRound(String carNamesString, int totalRounds, NumberGenerator numberGenerator,
                                           String ExpectedWinners) {
        RacingGame racingGame = new RacingGame(carNamesString, totalRounds, numberGenerator);

        // when
        racingGame.start();

        // then
        assertThat(outputStream.toString()).contains(ExpectedWinners);
    }

    // TODO: 메서드명 개선 필요. 각 라운드 결과만을 검증하기 위한 것인데, Outputs는 최종 우승자까지 포함된 뉘앙스
    private static Stream<Arguments> provideGameParametersAndExpectedEachRoundOutputs() {
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

    private static Stream<Arguments> provideGameParametersAndExpectedWinners() {
        return Stream.of(
                Arguments.of("pobi, woni", 1, new FixedNumberGenerator(MOVING_FORWARD), "최종 우승자 : pobi, woni"),
                Arguments.of("po_bi, wo ni, rude", 1, new FixedNumberGenerator(STOP), "최종 우승자 : po_bi, wo ni, rude")
        );
    }
}
