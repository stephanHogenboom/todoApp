package todoservice.systemtest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.protocol.HTTP;
import org.hamcrest.collection.IsEmptyIterable;
import org.junit.Test;
import todoservice.model.TaskBuilder;
import todoservice.model.TaskDto;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.core.Is.is;

/**
 * @author Stephan Hogenboom-
 * Simple test class that tests the basic crud functionality of the TaskController / TaskService
 */
public class TaskTest extends BaseTest {

    @Test
    public void testBasicCRUDActions() {
        List<TaskDto> taskDtosForTest = Arrays.asList(
                createTask("make app"),
                createTask("test app"),
                createTask("document app")
        );

        taskDtosForTest.forEach(task -> {
                    given()
                            .contentType(ContentType.JSON)
                            .body(createTask("make app"))
                            .post("/tasks")
                            .then().log().all()
                            .statusCode(HttpServletResponse.SC_OK);
                }
        );

        JsonPath jsonPath = given()
                .contentType(ContentType.JSON)
                .get("/tasks")
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .body().jsonPath();

        List<TaskDto> tasks = jsonPath.getList("", TaskDto.class);

        System.out.println(tasks);

        tasks.stream().map(task ->
                new TaskBuilder()
                        .setStatus("Done")
                        .setPriority("Hoog")
                        .setId(task.id)
                        .setStartDate(task.startDate)
                        .setDateOfCompletion(Date.from(Instant.now().plus(5, ChronoUnit.DAYS)))
                        .setName(task.name)
                        .setDeadline(task.deadline)
                        .build()
        ).forEach(task -> {
                    given()
                            .body(task).log().all()
                            .contentType(ContentType.JSON)
                            .put(String.format("/tasks/%s", task.id))
                            .then().log().all()
                            .statusCode(200);
                    given()
                            .body(task).log().all()
                            .contentType(ContentType.JSON)
                            .get(String.format("/tasks/%s", task.id))
                            .then().log().all()
                            .statusCode(200)
                            .body(containsString(String.valueOf(task.id)))
                            .body(containsString(task.status))
                            .body(containsString(task.name))
                            .body(containsString(String.valueOf(task.startDate.getDay())))
                            .body(containsString(String.valueOf(task.dateOfCompletion.getDay())));
                }
        );


        tasks.forEach(task -> {
            given()
                    .body(task).log().all()
                    .contentType(ContentType.JSON)
                    .delete(String.format("/tasks/%s", task.id))
                    .then().log().all()
                    .statusCode(200);
        });

        given()
                .contentType(ContentType.JSON)
                .get("/tasks")
                .then().log().all()
                .statusCode(204)
                .body(isEmptyString());
    }
}
