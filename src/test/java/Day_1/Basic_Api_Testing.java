package Day_1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

/*GET REQ
URL : https://reqres.in/api/users/1

POST REQ
URL : https://reqres.in/api/register 
Body : {
  "email": "eve.holt@reqres.in",  //Only these email will work rest will fail
  "password": "string"
}

PUT REQ
URL : https://reqres.in/api/custom-endpoints/2
Body : {
  "name": "string",
  "path": "string",
  "method": "GET",
  "response_data": {},
  "status_code": 0,
  "headers": {},
  "is_active": true
}

DELETE REQ
URL : https://reqres.in/api/custom/2 */

public class Basic_Api_Testing {
	public static String apiKey = "reqres-free-v1";
	int id;
//	GET REQ
	@Test(priority=0)
	void getSingleUser() {
		given()
			.header("x-api-key", apiKey)
		.when()
			.get("https://reqres.in/api/users/1")
		.then()
			.statusCode(200)
			.body("data.id",equalTo(1))
			.log().all();
	}
	
//	POST REQ
	@Test(priority=1)
	void createUser() {
//		Data for creating user
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("email", "eve.holt@reqres.in");
		data.put("password", "ronak");
		
		id = given()
			.header("x-api-key", apiKey)
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/register")
			.jsonPath().getInt("id");
		/*.then()
			.statusCode(200)
			.log().all();*/
	}
	
//	PUT REQ
	@Test(priority=2, dependsOnMethods= {"createUser"})
	void updateUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "ronak");
		data.put("job", "student");
		
		given()
			.header("x-api-key", apiKey)
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/custom-endpoints/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
//	DELETE REQ
	@Test(priority=4)
	void deleteUser() {
		given()
			.header("x-api-key", apiKey)
		.when()
			.delete("https://reqres.in/api/custom/"+id)
		.then()
			.log().all();
	}
}
