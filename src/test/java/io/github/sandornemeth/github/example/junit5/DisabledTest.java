package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DESCRIBEME
 *
 * @author Sandor Nemeth
 */
@DisplayName("Disabled test class")
@Disabled
class DisabledTest {

    @Test
    void aDisabledTest() {}

    @Test
    void aSecondDisabledTest() {}

}
