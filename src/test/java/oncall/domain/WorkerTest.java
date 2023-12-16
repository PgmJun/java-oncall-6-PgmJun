package oncall.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WorkerTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "aaaaaa", "adabawva", "우테코우테코"})
    void 근무자명_길이_검증_테스트(String name) {
        Assertions.assertThatThrownBy(() -> new Worker(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"이름", "코코", "코코아", "라따뚜이"})
    void 근무자명_길이_정상_테스트(String name) {
        new Worker(name);
    }

}
