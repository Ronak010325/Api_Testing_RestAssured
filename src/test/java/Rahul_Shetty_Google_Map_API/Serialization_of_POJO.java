package Rahul_Shetty_Google_Map_API;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Serialization_of_POJO {
    String baseURL = "https://rahulshettyacademy.com";
    @Test
    public void testSerialize() {
        Payload payloadBody = new Payload();
        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        payloadBody.setLocation(loc);
        payloadBody.setAccuracy(50);
        payloadBody.setName("Frontline house");
        payloadBody.setPhone_number("(+91) 983 893 3937");
        payloadBody.setAddress("29, side layout, cohen 09");
        List<String> types = Arrays.asList("shoe park", "shop");
        payloadBody.setTypes(types);
        payloadBody.setWebsite("http://google.com");
        payloadBody.setLanguage("French-IN");

        given()
                .body(payloadBody)
                .when()
                .post(baseURL+"/maps/api/place/add/json")
                .then()
                .statusCode(200)
                .body("status", equalTo("OK"))
                .log().all();
    }
}
