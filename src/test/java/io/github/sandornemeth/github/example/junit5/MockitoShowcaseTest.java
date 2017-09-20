package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Showcasing Mockito.
 *
 * Integration probably won't be available before Mockito 3.
 *
 * @author Sandor Nemeth
 */
@ExtendWith(MockitoExtension.class)
class MockitoShowcaseTest {

    @BeforeEach void setUp(@Mock MockedType mocked) {
        Mockito.when(mocked.getString()).thenReturn("A string");
    }

    @Test void simpleMockTest(@Mock MockedType mocked) {
        assertEquals("A string", mocked.getString());
    }

    interface MockedType {
        String getString();
    }
}
