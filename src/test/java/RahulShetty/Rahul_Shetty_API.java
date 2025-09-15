package RahulShetty;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Rahul_Shetty_API {
    @Test
    public void testmaps() {
        given()
                .contentType(ContentType.JSON)
                .body(Payload.addAddress())
                .when()
                .get("https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123")
                .then()
                .log().all();
    }
}