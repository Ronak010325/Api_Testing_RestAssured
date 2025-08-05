package Day_3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class pathQueryParameter {
	//https://reqres.in/api/users/1
	@Test
	void pathAndQuery() {
		given()
			.header("x-api-key", "reqres-free-v1")
			.pathParam("myPath", "users")		//Path Parameter
			.pathParam("id", 3)
//			.queryParam("page", 2)		//Query Parameter
//			.queryParam("id", 1)
		.when()
			.get("https://reqres.in/api/{myPath}/{id}")		//You don't have to specify query parameter in URL just the Path parameter
		.then()
			.statusCode(200)
			.log().all();
	}
}
