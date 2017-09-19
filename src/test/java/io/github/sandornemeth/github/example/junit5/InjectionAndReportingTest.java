package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@DisplayName("Injection and reporting")
class InjectionAndReportingTest {

    @Nested @DisplayName("Injection") class Injection {
        Injection(TestInfo testInfo) {
            System.out.println(toString(testInfo));
        }

        @BeforeEach void setUp(TestInfo testInfo) {
            System.out.println(toString(testInfo));
        }

        @Test void test(TestInfo testInfo) {
            System.out.println(toString(testInfo));
        }

        String toString(TestInfo testInfo) {
            String cls = testInfo.getTestClass().map(Class::getName).orElse("");
            String method = testInfo.getTestMethod().map(Method::getName).orElse("");

            return testInfo.getClass().getSimpleName() + " [" +
                    "displayName=" + testInfo.getDisplayName() + ", " +
                    "tags=" + testInfo.getTags() + ", " +
                    "class=" + cls + ", " +
                    "method=" + method + ", " +
                    "]";
        }
    }

    @Nested @DisplayName("reporting") class Reporting {
        @Test
        void report(TestReporter reporter) {
            reporter.publishEntry("entry 1", "value 1");

            Map<String, String> reportedEntries = new HashMap<>();
            reportedEntries.put("entry 2", "value 2");
            reportedEntries.put("entry 3", "value 3");

            reporter.publishEntry(reportedEntries);
        }
    }


}
