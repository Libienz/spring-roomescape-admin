package roomescape.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.exception.IllegalRequestFormException;

class GameTimeTest {
    @DisplayName("예약 가능 시각이 null인 경우 예외가 발생한다")
    @Test
    void createWithNullTest() {
        assertThatThrownBy(() -> new GameTime(null))
                .isInstanceOf(IllegalRequestFormException.class);
    }
}
