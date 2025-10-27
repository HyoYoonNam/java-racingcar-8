package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest(name = "[{index}] \"{0}\" -> 예외 발생")
    @ValueSource(strings = {
            "rude01",           // 순수한 영문 + 숫자 조합으로 5자를 초과
            "rude v", "r_udev"  // 허용된 스페이스나 언더 스코어를 포함하여 초과
    })
    @DisplayName("자동차 이름이 5자를 초과하면 예외를 발생시킨다")
    void constructor_throwsException_nameIsLongerThan5(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("이름은 5자를 넘을 수 없습니다: " + name);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0", "1,0", "2,0", "3,0",                 // 3 이하, 멈춤
            "4,1", "5,1", "6,1", "7,1", "8,1", "9,1"    // 4 이상, 전진
    })
    @DisplayName("숫자 값에 따라 전진하거나 멈출 수 있다")
    void move_moveForwardOrStop_byNumber(int number, int expectedPosition) {
        Car car = new Car("rude");

        car.move(number);

        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }
}
