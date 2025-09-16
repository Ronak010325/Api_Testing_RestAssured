package Day_6;

import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class schemaValidation {
	
	/* Response validation is different and Schema Validation is different */
	
	@Test
	public void testJSONSchema() {
		when()
				.get("http://localhost:4000/students")
				.then()
				.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("studentJSONSchema.json"));
	}
}