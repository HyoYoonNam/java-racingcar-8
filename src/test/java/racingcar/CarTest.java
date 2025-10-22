package racingcar;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @ParameterizedTest(name = "[{index}] \"{0}\" -> 예외 발생")
    @ValueSource(strings = {
            "po!bi",                                // 허용되지 않는 특수문자 1개
            "po__bi", "po_ bi", "po _bi", "po  bi", // 허용되는 특수문자지만, 사용 횟수 위반
            "루드비코"                                // 한글은 허용되지 않음

    })
    @DisplayName("자동차 이름이 유효하지 않은 패턴이면 예외를 발생시킨다")
    void constructor_throwsException_nameIsInvalidPattern(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("사용할 수 없는 이름입니다: " + name);
    }
}
