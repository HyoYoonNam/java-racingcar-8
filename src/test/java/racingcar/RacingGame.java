package racingcar;

import java.util.List;

public class RacingGame {

    public void start(String carNamesString, int totalRounds) {
        init(carNamesString, totalRounds);
    }

    private void init(String carNamesString, int totalRounds) {
        CarNameSeparator carNameSeparator = new CarNameSeparator();
        List<String> separated = carNameSeparator.separate(carNamesString);
        if (separated.size() < 2) {
            throw new IllegalArgumentException("참가하는 자동차 수가 2개 미만입니다. 자동차의 수나 구분자를 확인해주세요: " + carNamesString);
        }
        if (totalRounds < 1) {
            throw new IllegalArgumentException("게임의 총 진행 회차는 1회 이상이어야 합니다: " + totalRounds);
        }
    }
}
