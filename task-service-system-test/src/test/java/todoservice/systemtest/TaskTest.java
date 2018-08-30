package todoservice.systemtest;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.collection.IsArray;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.collection.IsEmptyIterable;
import org.junit.Test;
import todoservice.model.TaskDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TaskTest extends BaseTest {

    @Test
    public void testBasicCRUDActions() {
        given()
                .contentType(ContentType.JSON)
                .body(createTask("make app"))
                .post("/tasks")
        .then().log().all()
                .statusCode(HttpServletResponse.SC_OK);

        JsonPath jsonPath = given()
                .contentType(ContentType.JSON)
                .get("/tasks" )
        .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .body().jsonPath();

        List<TaskDto> tasks = jsonPath.getList("", TaskDto.class);

        System.out.println(tasks);

        tasks.forEach(task -> {
                given()
                        .body(task).log().all()
                        .delete(String.format("/tasks/%s", task.id))
                        .then().log().all()
                        .statusCode(200);
                });

        given()
                .contentType(ContentType.JSON)
                .get("/tasks" )
        .then().log().all()
                .statusCode(404)
                .body("", IsEmptyIterable.emptyIterable());
    }
}
