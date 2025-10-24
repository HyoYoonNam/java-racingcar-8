package racingcar;

import java.util.List;
import java.util.Set;

public class RacingGame {

    public void start(String carNamesString, int totalRounds) {
        init(carNamesString, totalRounds);
    }

    private void init(String carNamesString, int totalRounds) {
        ParameterValidator.validate(carNamesString, totalRounds);
    }

    // TODO: validate를 제외한 나머지 메서드를 RacingGame에서 호출할 수 없도록 하는 캡슐화를 고려할 필요가 있다.
    private static class ParameterValidator {

        private static void validate(String carNamesString, int totalRounds) {
            validateCarNamesString(carNamesString);
            validateTotalRounds(totalRounds);
        }

        private static void validateCarNamesString(String carNamesString) {
            CarNameSeparator carNameSeparator = new CarNameSeparator();
            List<String> separated = carNameSeparator.separate(carNamesString);

            validateCarCount(carNamesString, separated);

            validateCarNamesDuplicated(carNamesString, separated);
        }

        private static void validateCarCount(String carNamesString, List<String> separated) {
            if (separated.size() < 2) {
                throw new IllegalArgumentException("참가하는 자동차 수가 2개 미만입니다. 자동차의 수나 구분자를 확인해주세요: " + carNamesString);
            }
        }

        private static void validateCarNamesDuplicated(String carNamesString, List<String> separated) {
            Set<String> carNames = Set.copyOf(separated);
            if (separated.size() != carNames.size()) {
                throw new IllegalArgumentException("중복된 자동차 이름이 존재합니다: " + carNamesString);
            }
        }

        private static void validateTotalRounds(int totalRounds) {
            if (totalRounds < 1) {
                throw new IllegalArgumentException("게임의 총 진행 회차는 1회 이상이어야 합니다: " + totalRounds);
            }
        }
    }
}
