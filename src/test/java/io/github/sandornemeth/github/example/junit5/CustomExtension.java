package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * A custom extension for JUnit tests.
 *
 * @author Sandor Nemeth
 */
public class CustomExtension implements
        BeforeAllCallback,
        AfterAllCallback,
        BeforeEachCallback,
        AfterEachCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback{
    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("Extension - After all");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("Extension - After each");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("Extension - after test");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("Extension - before all");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("Extension - before each");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("Extension - before test");
    }
}
