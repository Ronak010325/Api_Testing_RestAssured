package Day_4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class parsingJSONResponse {
	@Test(enabled=false)
	void directValidationThenMethod() {
		given()
			.contentType("ContentType.JSON")
		.when()
			.get("http://localhost:3000/students")
		.then()
			.body("Students[3].courses[1]", equalTo("JavaScript"));
	}
	
	@Test(enabled=false)
	void res_Vairable() {
		Response res = given().contentType("ContentType.JSON").when().get("http://localhost:3000/students");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		Assert.assertEquals(res.jsonPath().get("Students[3].courses[1]").toString(), "JavaScript");
	}
	
	@Test(enabled=true)
	void JsonObject() {
		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/students");
//		System.out.println(res.toString());
		JSONObject jo = new JSONObject(res.asString());
		JSONArray resArray = jo.getJSONArray("Students");
		boolean status = false;
		int sum = 0;
		for(int i = 0 ; i < resArray.length() ; i++) {
//			if(resArray.getJSONObject(i).get("name").toString().equals("Rahul Sharma")) {
//				status = true;
//				break;
//			}
			int num = Integer.parseInt(resArray.getJSONObject(i).get("id").toString());
			sum += num;
		}
//		Assert.assertEquals(status, true);
		System.out.println(sum);
		Assert.assertEquals(sum, 10);
	}
	
}
