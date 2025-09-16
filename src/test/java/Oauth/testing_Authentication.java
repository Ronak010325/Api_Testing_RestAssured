package Oauth;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class testing_Authentication {
    String base_URL = "https://rahulshettyacademy.com";
    String access_token;
    @Test
    public void testOauth() {
        Response res = given()
                .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when()
                .post(base_URL+"/oauthapi/oauth2/resourceOwner/token");
        JSONObject obj = new JSONObject(res.asString());
        access_token = obj.get("access_token").toString();
        System.out.println(access_token);
    }

    @Test(dependsOnMethods = {"testOauth"})
    public void testAccessingCourseDetails() {
        given()
                .queryParam("access_token",access_token)
                .when()
                .get(base_URL+"/oauthapi/getCourseDetails")
                .then()
                .log().all();
    }
}
