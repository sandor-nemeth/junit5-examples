package io.github.sandornemeth.github.example.junit5;

import lombok.Value;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("basic")
class BasicTest {

    @Test
    @Tag("fast")
    @DisplayName("Test standard assertions")
    void standardAssertionTest() {
        assertEquals(2, 2);
        assertEquals(4, 4, () -> "Lazy message to be displayed");
    }

    @Test
    @Tag("fast")
    @DisplayName("Grouped assertions")
    void groupedAssertions() {
        assertAll("numbers",
                () -> assertEquals(2, 2, "2 should be 2"),
                () -> assertEquals(3, 3, "3 should be 3")
        );

        Person p = new Person("john", "doe");
        assertAll("person data",
                () -> {
                    String firstName = p.getFirstName();
                    assertAll("firstName",
                            () -> assertTrue(firstName.startsWith("j"), "first name starts with j"),
                            () -> assertTrue(firstName.length() == 4, "first name has length of 4")
                    );
                },
                () -> {
                    String lastName = p.getLastName();
                    assertAll("lastName",
                            () -> assertTrue(lastName.startsWith("d"), "last name starts with d"),
                            () -> assertTrue(lastName.length() == 3, "last name has length of 3")
                    );
                }
        );
    }

    @Test
    @Tag("slow")
    @DisplayName("Timeout assertions")
    void timeoutTest() {
        String actualResult = assertTimeout(Duration.ofMillis(50), () -> {
            return "a result";
        });
        assertEquals(actualResult, "a result");

        // exceed timeout
        assertAll("timeouts",
                () -> assertTimeout(Duration.ofMillis(50), () -> Thread.sleep(100)),
                () -> assertTimeoutPreemptively(Duration.ofMillis(50), () -> Thread.sleep(100))
        );
    }

    @Test
    @DisplayName("Testing an exception")
    void exceptionTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    @DisplayName("Assumption test")
    void assumptions() {
        String value = "a value";
        assumeTrue(value == "value", () -> "Aborting test!");
        assertTrue(true);
    }

    @Test @DisplayName("A skipped test") @Disabled
    void skipped() {}

    @Value
    static class Person {
        String firstName;
        String lastName;
    }

}