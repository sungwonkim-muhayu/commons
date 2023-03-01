package org.github.swsz2;

import org.assertj.core.api.Assertions;
import org.github.swsz2.commons.utils.ExpressionUtils;
import org.junit.jupiter.api.Test;

public class VerbalExpressionTest {

    @Test
    void shouldPassWhenEnteredValidPhoneNumber() {
        Throwable throwable = Assertions.catchThrowable(() -> ExpressionUtils.checkValidPhoneNumber("000-0000-0000"));
        Assertions.assertThat(throwable).isNull();
    }

    @Test
    void shouldThrowWhenEnteredInvalidPhoneNumber() {
        Throwable throwable = Assertions.catchThrowable(() -> ExpressionUtils.checkValidPhoneNumber("000-0000-000"));
        Assertions.assertThat(throwable).isNotNull();
    }
}
