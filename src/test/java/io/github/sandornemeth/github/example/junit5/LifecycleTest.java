package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Lifecycle test")
class LifecycleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Hello, I am before all");
    }

    @BeforeEach
    void setUp() {
        System.out.println("    I am running before each test");
    }

    @Test
    @DisplayName("LifeCycle test 1")
    void test1() {
        System.out.println("        I am test 1");
    }

    @Test
    @DisplayName("LifeCycle test 2")
    void test2() {
        System.out.println("        I am test 2");
    }

    @AfterEach
    void tearDown() {
        System.out.println("    I am running after each test");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Hello I am after all.");
    }
}
