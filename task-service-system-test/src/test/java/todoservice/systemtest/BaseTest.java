package todoservice.systemtest;

import org.junit.Test;
import todoservice.model.TaskBuilder;
import todoservice.model.TaskDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public abstract class BaseTest {
    final protected static String BASE_URL = getEnvOrDefault("BASE_URL", "http://localhost:8080");



    protected TaskDto createTask(String name) {
        return new TaskBuilder()
                .setStartDate(Date.from(Instant.now()))
                .setDeadline(Date.from(Instant.now().plus(2L, ChronoUnit.DAYS)))
                .setName(name)
                .setPriority("very high")
                .setStatus("in progress")
                .build();
    }

    private static String getEnvOrDefault(String environmentValue, String defaultValue) {
        Optional<String> value = Optional.ofNullable(System.getenv(environmentValue));
        return value.orElse(defaultValue);
    }

    @Test
    public void testEnvOrDefault() {
        System.out.println(getEnvOrDefault("BASE_URL", "localhost:8080"));
    }
}
