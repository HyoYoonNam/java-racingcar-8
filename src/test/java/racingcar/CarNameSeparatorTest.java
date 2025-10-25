package racingcar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CarNameSeparatorTest {

    @ParameterizedTest(name = "[{index}] \"{0}\" -> {1}")
    @MethodSource("provideCarNameWithNoWhitespace")
    @DisplayName("자동차 이름을 쉼표 기준으로 분리한다")
    void separateCarNameByComma(String carNamesString, List<String> carNames) {
        List<String> separated = CarNameSeparator.separate(carNamesString);

        assertThat(separated).isEqualTo(carNames);
    }

    @ParameterizedTest(name = "[{index}] \"{0}\" -> {1}")
    @MethodSource("provideCarNameWithWhitespace")
    @DisplayName("자동차 이름을 분리할 때, 각 요소가 될 문자열의 앞 또는 뒤에 공백이 존재한다면, 공백을 제거한다")
    void stripWhitespace(String carNamesString, List<String> carNames) {
        List<String> separated = CarNameSeparator.separate(carNamesString);

        assertThat(separated).isEqualTo(carNames);
    }

    private static Stream<Arguments> provideCarNameWithNoWhitespace() {
        return Stream.of(
                Arguments.of("pobi,woni,jun", List.of("pobi", "woni", "jun")),
                Arguments.of("pobi", List.of("pobi")),
                Arguments.of("루드,비코", List.of("루드", "비코")),
                Arguments.of("루드", List.of("루드")),
                // "자동차 이름은 5자 이하만 가능하다"라는 요구 사항은 자동차(Car) 도메인의 제약이므로 여기서는 테스트가 실패하면 안 됩니다.
                Arguments.of("length is longer than 5,5자 초과 이름", List.of("length is longer than 5", "5자 초과 이름"))
        );
    }

    private static Stream<Arguments> provideCarNameWithWhitespace() {
        return Stream.of(
                Arguments.of(" pobi, woni, jun", List.of("pobi", "woni", "jun")),
                Arguments.of("pobi ,woni ,jun ", List.of("pobi", "woni", "jun")),
                Arguments.of(" pobi , woni , jun ", List.of("pobi", "woni", "jun")),
                Arguments.of(" \t  pobi , woni\n , jun ", List.of("pobi", "woni", "jun")),
                Arguments.of("\t루드 , 비코\n", List.of("루드", "비코"))
        );
    }
}
