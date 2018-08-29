package todoservice.systemtest;

import io.restassured.http.ContentType;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class TaskTest extends BaseTest {

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
