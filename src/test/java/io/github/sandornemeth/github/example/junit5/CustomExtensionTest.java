package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Test with custom extensions.
 *
 * @author Sandor Nemeth
 */
@ExtendWith(CustomExtension.class)
class CustomExtensionTest {

    @Test
    void first() {
        System.out.println("first");
    }

    @Test
    void second() {
        System.out.println("first");
    }

}
