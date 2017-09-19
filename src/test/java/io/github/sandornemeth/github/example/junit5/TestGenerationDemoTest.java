package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Generating tests")
class TestGenerationDemoTest {

    @RepeatedTest(value = 10, name = "{displayName}: repetition {currentRepetition} of {totalRepetitions}")
    @DisplayName("Repeat!")
    void repetition(RepetitionInfo info) {
        System.out.println(String.format("Current repetition: %d out of %d", info.getCurrentRepetition(),
                info.getTotalRepetitions()));
    }

    @Nested
    @DisplayName("when parameterized")
    class ParametersTest {

        void setUp() {
            System.out.println("I am running before all parameterized tests!");
        }

        @DisplayName("Simple parameterized test")
        @ParameterizedTest
        @ValueSource(strings = {"string 1", "string 2"})
        void simpleParameterizedTest(String value) {
            System.out.println("The value is: " + value);
        }

        @DisplayName("Parameterized CSV test")
        @ParameterizedTest
        @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
        void csvParameterizedTest(String param1, int param2) {
            System.out.println(String.format("Param1: %s, Param2: %d", param1, param2));
        }

        @DisplayName("CSV File parameters")
        @ParameterizedTest
        @CsvFileSource(resources = {"/test.csv"})
        void parametersFromCsvFile(String param1, int param2) {
            System.out.println(String.format("Param1: %s, Param2: %d", param1, param2));
        }
    }

    @Nested
    @DisplayName("When dynamic")
    class WhenDynamic {

        @BeforeEach
        void setUp() {
            System.out.println("I am only running for the @TestFactory methods");
        }

        @TestFactory
        Collection<DynamicTest> dynamicTestsFromCollection() {
            return Arrays.asList(
                    dynamicTest("1st dynamic test", () -> assertTrue(true)),
                    dynamicTest("2nd dynamic test", () -> assertEquals(4, 2 * 2))
            );
        }

        @TestFactory
        Iterable<DynamicTest> dynamicTestsFromIterable() {
            return Arrays.asList(
                    dynamicTest("3rd dynamic test", () -> assertTrue(true)),
                    dynamicTest("4th dynamic test", () -> assertEquals(4, 2 * 2))
            );
        }

        @TestFactory
        Iterator<DynamicTest> dynamicTestsFromIterator() {
            return Arrays.asList(
                    dynamicTest("5th dynamic test", () -> assertTrue(true)),
                    dynamicTest("6th dynamic test", () -> assertEquals(4, 2 * 2))
            ).iterator();
        }

        @TestFactory
        Stream<DynamicTest> dynamicTestsFromStream() {
            return Stream.of("A", "B", "C")
                    .map(str -> dynamicTest("test" + str, () -> { /* ... */ }));
        }

        @TestFactory
        Stream<DynamicTest> dynamicTestsFromIntStream() {
            // Generates tests for the first 10 even integers.
            return IntStream.iterate(0, n -> n + 2).limit(10)
                    .mapToObj(n -> dynamicTest("test" + n, () -> assertTrue(n % 2 == 0)));
        }

        @TestFactory
        Stream<DynamicTest> generateRandomNumberOfTests() {

            // Generates random positive integers between 0 and 100 until
            // a number evenly divisible by 7 is encountered.
            Iterator<Integer> inputGenerator = new Iterator<Integer>() {

                Random random = new Random();
                int current;

                @Override
                public boolean hasNext() {
                    current = random.nextInt(100);
                    return current % 7 != 0;
                }

                @Override
                public Integer next() {
                    return current;
                }
            };

            // Generates display names like: input:5, input:37, input:85, etc.
            Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

            // Executes tests based on the current input value.
            ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

            // Returns a stream of dynamic tests.
            return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
        }

        @TestFactory
        Stream<DynamicNode> dynamicTestsWithContainers() {
            return Stream.of("A", "B", "C")
                    .map(input -> dynamicContainer("Container " + input, Stream.of(
                            dynamicTest("not null", () -> assertNotNull(input)),
                            dynamicContainer("properties", Stream.of(
                                    dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
                                    dynamicTest("not empty", () -> assertFalse(input.isEmpty()))
                            ))
                    )));
        }
    }



}
