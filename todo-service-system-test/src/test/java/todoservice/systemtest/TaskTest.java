package todoservice.systemtest;

import io.restassured.http.ContentType;
import org.junit.Test;
import todoservice.model.TaskDto;

import javax.servlet.http.HttpServletResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class TaskTest extends BaseTest {

    @Test
    public void testInsertTask() {
        given()
                .contentType(ContentType.JSON)
                .body(createTask("make app"))
                .post("/tasks")
        .then().log().all()
                .statusCode(HttpServletResponse.SC_OK);

        TaskDto task = given()
                .contentType(ContentType.JSON)
                .get("tasks" )
        .then().log().all()
                .root("find{task -> trip.name=='make app'}")
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(TaskDto.class);//get a specific trip

        System.out.println(task);
    }

    @Test
    public void testGetTasks() {
        given()
                .contentType(ContentType.JSON)
                .get(String.format("%s/task" , BASE_URL))
        .then().log().all()
                .statusCode(HttpServletResponse.SC_OK)
                .body(is(""));

    }

}
