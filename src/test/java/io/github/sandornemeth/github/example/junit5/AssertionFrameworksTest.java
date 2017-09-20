package io.github.sandornemeth.github.example.junit5;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Example for tests using different assertions.
 *
 * @author Sandor Nemeth
 */
class AssertionFrameworksTest {

    @Test
    @DisplayName("Hamcrest assertions")
    void hamcrestAssertionTest() {
        MatcherAssert.assertThat("something could be wrong", 2, Matchers.equalTo(2));
    }

    @Test @DisplayName("AssertJ assertions")
    void assertjTest() {
        Assertions.assertThat(2).isEqualTo(2);
    }
}
