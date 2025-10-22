package racingcar;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarNameSeparatorTest {

    @Test
    @DisplayName("자동차 이름을 쉼표 기준으로 분리한다")
    void separateCarNameByComma() {
        String carname = "pobi,woni,jun";
        CarNameSeparator carNameSeparator = new CarNameSeparator();
        List<String> carNameList = carNameSeparator.separate(carname);
        assertThat(carNameList).isEqualTo(List.of("pobi", "woni", "jun"));
    }
}
