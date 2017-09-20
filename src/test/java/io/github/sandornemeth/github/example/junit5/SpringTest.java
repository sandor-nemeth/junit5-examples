package io.github.sandornemeth.github.example.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringTest.TestConfig.class)
class SpringTest {

    @Test void springTest() {}

    @Configuration
    static class TestConfig {
        @Bean(name = {"teststring"})
        String testString() {
            return "A test string";
        }
    }
}
