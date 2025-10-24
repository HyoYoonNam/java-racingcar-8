package racingcar;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RacingGameTest {

    @ParameterizedTest(name = "[{index}] (\"{0}\", {1}) -> 예외 발생")
    @CsvSource(value = {"rudev:2", "ru_de:2", "ru de:2", "'':2"}, // CsvSource는 빈 문자열을 "''"로 정의합니다. ":2"처럼 비우면 null입니다.
            delimiter = ':')
    @DisplayName("참가하는 자동차가 2개 미만이라면 게임을 시작할 수 없다")
    void constructor_throwsException_carCountIsOne(String carNamesString, int totalRounds) {
        assertThatThrownBy(() -> new RacingGame().start(carNamesString, totalRounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("참가하는 자동차 수가 2개 미만입니다. 자동차의 수나 구분자를 확인해주세요: " + carNamesString);
    }
}
