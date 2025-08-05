package Day_3;

import static io.restassured.RestAssured.given;

import java.util.Collection;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class cookiesHeader {
	@Test(enabled=false)
	void testCookies1() {
		given()
		.when()
			.get("https://www.google.com")
		.then()
			.cookie("AEC", "AVh_V2gZ5hL4Bg4cPPU3rPy6dqX420qYNSwA9G9ms7Z9EPrH0Nh4pqFXEw")
			.log().all();
	}
	
	@Test(enabled=true)
	void testCookies2() {
		Response res = given().when().get("https://www.google.com");
//		Getting Single Cookie info
		String cookie = res.getCookie("AEC");
		System.out.println("Cookie : "+cookie);
//		Getting All cookies info
		Map<String, String> cookies = res.getCookies();
//		for(String i : cookies.keySet()) {
//			String cookieInstance = res.getCookie(i);
//			System.out.println(i+" : "+cookieInstance);
//		}
//		To print all the values from Map
		for(String i : cookies.values()) {
			System.out.println(i);
		}
	}
	
}
