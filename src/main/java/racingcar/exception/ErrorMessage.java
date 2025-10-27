package racingcar.exception;

public enum ErrorMessage {
    APPLICATION_ILLEGAL_ARGUMENT_EX("[ERROR] 오류가 발생했습니다: "),
    CAR_NAME_INVALID("사용할 수 없는 이름입니다: "),
    CAR_NAME_LENGTH_GREATER_THAN_MAXIMUM("이름은 5자를 넘을 수 없습니다: "),
    CAR_NAME_DUPLICATED("중복된 자동차 이름이 존재합니다: "),
    CAR_COUNT_LESS_THAN_MINIMUM("참가하는 자동차 수가 2개 미만입니다. 자동차의 수나 구분자를 확인해주세요: "),
    ROUNDS_LESS_THAN_MINIMUM("게임의 총 진행 회차는 1회 이상이어야 합니다: "),
    INPUT_LINE_NO_EXISTS("입력이 존재하지 않습니다"),
    INPUT_LINE_DOES_NOT_NUMBER("입력한 값이 숫자가 아닙니다"),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String build(String target) {
        return this.message + target;
    }

    public String build(int target) {
        return this.message + target;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
