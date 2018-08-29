package todoservice.systemtest;

import org.junit.Test;

import java.util.Optional;

public abstract class BaseTest {
    final protected static String BASE_URL = System.getenv("BASE_URL");




    private String getEnvOrDefault(String environmentValue, String defaultValue) {
        Optional<String> value = Optional.ofNullable(System.getenv(environmentValue));
        return value.orElse(defaultValue);
    }

    @Test
    public void testEnvOrDefault() {
        System.out.println(getEnvOrDefault("BASE_URL", "localhost:8080"));
    }
}
