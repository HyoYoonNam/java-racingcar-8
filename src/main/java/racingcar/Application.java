package racingcar;

public class Application {

    public static void main(String[] args) {
        String carNames = InputView.readCarNames();
        int totalRounds = InputView.readTotalRounds();

        RacingGame game = new RacingGame(carNames, totalRounds);
        game.start();
    }
}
