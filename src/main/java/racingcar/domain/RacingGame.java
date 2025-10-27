package racingcar.domain;

import java.util.List;
import java.util.Set;

import racingcar.numbergenerator.NumberGenerator;
import racingcar.numbergenerator.RandomNumberGenerator;
import racingcar.separator.CarNameSeparator;
import racingcar.view.OutputView;

public class RacingGame {

    private static final int MIN_CAR_COUNT = 2;
    private static final int MIN_TOTAL_ROUNDS = 1;

    private final List<Car> cars;
    private final int totalRounds;
    private final NumberGenerator numberGenerator;

    private int roundCount = 0;

    public RacingGame(String carNamesString, int totalRounds) {
        ParameterValidator.validate(carNamesString, totalRounds);
        this.cars = createCars(carNamesString);
        this.totalRounds = totalRounds;
        this.numberGenerator = new RandomNumberGenerator(0, 9);
    }

    public RacingGame(String carNamesString, int totalRounds, NumberGenerator numberGenerator) {
        ParameterValidator.validate(carNamesString, totalRounds);
        this.cars = createCars(carNamesString);
        this.totalRounds = totalRounds;
        this.numberGenerator = numberGenerator;
    }

    public void start() {
        OutputView.printHeader();

        while (++roundCount <= totalRounds) {
            for (Car car : cars) {
                car.move(numberGenerator.generate());
            }
            OutputView.printCarPositions(cars);
        }

        List<String> winners = findWinners(cars);
        OutputView.printWinners(winners);
    }

    private static List<Car> createCars(String carNamesString) {
        return CarNameSeparator.separate(carNamesString).stream()
                .map(Car::new)
                .toList();
    }

    private static List<String> findWinners(List<Car> cars) {
        int maxPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max() // TODO: 비즈니스 로직상 해당 값이 무조건 존재하는데, 안전한 처리를 위해 orElse를 넣어야 되는지 고민됨.
                .orElse(0); // TODO: 오히려 orElse가 'null' 가능성이 있다고 착각을 유발하는 단점이 있음. 최종 판단(orElse 대신 getAsInt 사용)은 유보함.

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .toList();
    }

    // TODO: validate를 제외한 나머지 메서드를 RacingGame에서 호출할 수 없도록 하는 캡슐화를 고려할 필요가 있다.
    private static class ParameterValidator {

        private static void validate(String carNamesString, int totalRounds) {
            validateCarNamesString(carNamesString);
            validateTotalRounds(totalRounds);
        }

        private static void validateCarNamesString(String carNamesString) {
            List<String> separated = CarNameSeparator.separate(carNamesString);

            validateCarCount(carNamesString, separated);

            validateCarNamesDuplicated(carNamesString, separated);
        }

        private static void validateCarCount(String carNamesString, List<String> separated) {
            if (separated.size() < MIN_CAR_COUNT) {
                throw new IllegalArgumentException("참가하는 자동차 수가 " + MIN_CAR_COUNT + "개 미만입니다."
                        + " 자동차의 수나 구분자를 확인해주세요: " + carNamesString);
            }
        }

        private static void validateCarNamesDuplicated(String carNamesString, List<String> separated) {
            Set<String> carNames = Set.copyOf(separated);
            if (separated.size() != carNames.size()) {
                throw new IllegalArgumentException("중복된 자동차 이름이 존재합니다: " + carNamesString);
            }
        }

        private static void validateTotalRounds(int totalRounds) {
            if (totalRounds < MIN_TOTAL_ROUNDS) {
                throw new IllegalArgumentException("게임의 총 진행 회차는 " + MIN_TOTAL_ROUNDS + "회 이상이어야 합니다: " + totalRounds);
            }
        }
    }
}
