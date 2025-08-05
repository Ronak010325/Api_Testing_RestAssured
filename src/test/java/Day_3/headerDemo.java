package Day_3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class headerDemo {
	@Test(enabled=false)
	void testHeader() {
		given()
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.log().all();
	}
	@Test(enabled=true)
	void getHeaders() {
		Response res = given().when().get("https://www.google.com");
//		Getting Single Header
		String contentType = res.getHeader("Content-Type");
		System.out.println("Content Type : "+contentType);
		
//		Getting all headers
		Headers headers = res.getHeaders();
		for(Header i : headers) {
//			System.out.println(res.getHeader(i.getName()));
			System.out.println(i.getName()+" : "+i.getValue());
		}
	}
}
