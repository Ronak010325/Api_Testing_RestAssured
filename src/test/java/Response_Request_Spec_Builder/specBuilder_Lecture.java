package Response_Request_Spec_Builder;

import Rahul_Shetty_POJO_ComplexJSON.*;
        import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class specBuilder_Lecture {
    String base_URL = "https://rahulshettyacademy.com";
    public String access_token = "";
    RequestSpecification req = new RequestSpecBuilder()
            .addFormParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
            .addFormParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
            .addFormParam("grant_type", "client_credentials")
            .addFormParam("scope", "trust")
            .setBaseUri(base_URL)
            .build();

    ResponseSpecification res = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .log(LogDetail.ALL)
            .build().contentType(ContentType.JSON);

    @Test(enabled = true)
    public void testOauth() {
        Response res = given()
                .spec(req)
                .when()
                .post("/oauthapi/oauth2/resourceOwner/token");
        JSONObject obj = new JSONObject(res.asString());
        access_token = obj.get("access_token").toString();
        System.out.println(access_token);
        Assert.assertFalse(obj.get("access_token").toString().isEmpty());
    }

    @Test(dependsOnMethods = {"testOauth"}, enabled = true)
    public void testAccessingResponseDetails() {
        given()
                .queryParam("access_token", access_token)
                .when()
                .get(base_URL+"/oauthapi/getCourseDetails")
                .then()
                .spec(res)
                .extract().response();
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }
}
