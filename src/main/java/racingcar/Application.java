package racingcar;

import racingcar.domain.RacingGame;
import racingcar.exception.ErrorMessage;
import racingcar.view.InputView;

public class Application {

    public static void main(String[] args) {

        try {
            String carNames = InputView.readCarNames();
            int totalRounds = InputView.readTotalRounds();

            RacingGame game = new RacingGame(carNames, totalRounds);
            game.start();
        } catch (IllegalArgumentException e) {
            // (개발자가 아닌) 일반 사용자용 친절한 오류 메시지
            System.out.println(ErrorMessage.APPLICATION_ILLEGAL_ARGUMENT_EX.build(e.getMessage()));
            // 개발자 stack trace용 예외 그대로 던지기.
            // 그리고 ApplicationTest에서 main이 예외 던지는 걸 테스트하기 때문에도 필요함.단순히 잡고 콘솔 출력만 해주면 테스트 통과 불가능
            throw e;
        }
    }
}
