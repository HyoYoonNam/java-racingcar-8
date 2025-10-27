package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RacingGameTest {

    @ParameterizedTest(name = "[{index}] (\"{0}\", {1}) -> 예외 발생")
    @CsvSource(value = {"rudev:2", "ru_de:2", "ru de:2", "'':2"}, // CsvSource는 빈 문자열을 "''"로 정의합니다. ":2"처럼 비우면 null입니다.
            delimiter = ':')
    @DisplayName("참가하는 자동차가 2개 미만이라면 게임을 시작할 수 없다")
    void constructor_throwsException_carCountIsOne(String carNamesString, int totalRounds) {
        assertThatThrownBy(() -> new RacingGame(carNamesString, totalRounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("참가하는 자동차 수가 2개 미만입니다. 자동차의 수나 구분자를 확인해주세요: " + carNamesString);
    }

    /* TODO:
     * "rude,vico:''"와 같이 totalRounds에 빈 문자열이 입력되는 경우에 대한 예외 처리가 애매하다.
     * (지금 생각으로는) 문제에서 Console.readLine만을 제공하기 때문에 Application에서 타입 변환을 시도하고,
     * 예외를 처리하는 것이 적절해보인다. 일단 RacingGameTest에 넣기는 애매해서 생략하고 진행한다.
     */
    @ParameterizedTest(name = "[{index}] (\"{0}\", {1}) -> 예외 발생")
    @CsvSource(value = {"rude,vico:0"},
            delimiter = ':')
    @DisplayName("레이싱 게임의 총 진행 회차가 1회 미만이라면 게임을 시작할 수 없다")
    void constructor_throwsException_totalRounds(String carNamesString, int totalRounds) {
        assertThatThrownBy(() -> new RacingGame(carNamesString, totalRounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("게임의 총 진행 회차는 1회 이상이어야 합니다: " + totalRounds);
    }

    @ParameterizedTest(name = "[{index}] (\"{0}\", {1}) -> 예외 발생")
    @CsvSource(value = {"ru_de,ru_de:1", "pobi,pobi:2", "rude,vico,rude:1"},
            delimiter = ':')
    @DisplayName("참가하는 자동차 이름에 중복이 있다면 게임을 시작할 수 없다")
    void constructor_throwsException_carNamesAreDuplicated(String carNamesString, int totalRounds) {
        assertThatThrownBy(() -> new RacingGame(carNamesString, totalRounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("중복된 자동차 이름이 존재합니다: " + carNamesString);
    }

    @Test
    @DisplayName("정상적인 입력(자동차 이름과 시도할 횟수)에 대해 게임을 성공적으로 생성한다")
    void constructor_success_validInput() {
        String carNamesString = "pobi, woni, jun";
        int totalRounds = 1;

        RacingGame racingGame = new RacingGame(carNamesString, totalRounds);

        assertThat(racingGame).isNotNull();
    }
}
