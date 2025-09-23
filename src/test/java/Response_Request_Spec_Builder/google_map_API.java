package Response_Request_Spec_Builder;

import Rahul_Shetty_Google_Map_API.Location;
import Rahul_Shetty_Google_Map_API.Payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class google_map_API {
    String baseURL = "https://rahulshettyacademy.com";
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(baseURL)
            .setContentType(ContentType.JSON)
            .build();
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
        Response res = given()
                .spec(req)
                .body(payloadBody)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .statusCode(200)
                .extract().response();
        String responseString = res.asString();
        System.out.println(responseString);
    }
}
