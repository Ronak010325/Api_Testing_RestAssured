package Day_3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class logDemo {
	@Test(enabled=true)
	void testHeader() {
		given()
		.when()
			.get("https://www.google.com")
		.then()
//			.log().all();	//To Print Whole Response
//			.log().body();	//To Print Only Body
//			.log().cookies();	//To Print Cookies
			.log().headers();	//To Print Headers
	}
}
