package Day_5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class parsingXMLResponse {
	@Test(enabled=true)
	public void testApprouch1() {
		given()
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("Students.Student[0].name", equalTo("Ronak Yadav"))
			.log().all();
	}
	
	@Test(enabled=false)
	public void testApprouch2() {
		Response res = given().when().get("http://localhost:3000/students/xml");
		
//		Basic Validation
//		Assert.assertEquals(res.statusCode(), 200);
//		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
//		Assert.assertEquals(res.xmlPath().get("Students.Student[0].name").toString(), "Ronak Yadav");
		
//		Additional Validation
		XmlPath xmlObj = new XmlPath(res.asString()); 
		List<String> students = xmlObj.getList("Students.Student");
		Assert.assertEquals(students.size(), 4);
		
		List<String> studentNames = xmlObj.getList("Students.Student.name");
		boolean status = false;
		for(int i = 0 ; i < studentNames.size() ; i++) {
			if(studentNames.get(i).equals("Priya Singh")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
	
}