package racingcar.view;

import java.util.NoSuchElementException;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView() {
    }

    public static String readCarNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return readLine();
    }

    public static int readTotalRounds() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        return readInt();
    }

    private static String readLine() {
        String line = null;

        try {
            line = Console.readLine();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("입력이 존재하지 않습니다", e);
        }

        return line;
    }

    private static int readInt() {
        int userInput = 0;

        try {
            userInput = Integer.parseInt(Console.readLine());
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("입력이 존재하지 않습니다", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력한 값이 숫자가 아닙니다", e);
        }

        return userInput;
    }
}
