package Rahul_Shetty_POJO_ComplexJSON;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class payload_using_POJO_class {
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
        Assert.assertFalse(obj.get("access_token").toString().isEmpty());
        access_token = obj.get("access_token").toString();
    }

    @Test(dependsOnMethods = {"testOauth"})
    public void testAccessingResponseDetails() {
        RequestSpecification req = new RequestSpecBuilder().addQueryParam("access_token", access_token).build();
        Payload_Pojo_class res = given()
                .spec(req)
                .when()
                .get(base_URL+"/oauthapi/getCourseDetails").as(Payload_Pojo_class.class);
        Courses courses = res.getCourses();

//        Printing all the fields from Response
        System.out.println(res.getInstructor());
        System.out.println(res.getUrl());
        System.out.println(res.getServices());
        System.out.println(res.getExpertise());
        System.out.println("Courses :- ");
//        WebAutomation
        List<WebAutomation> webautomation = courses.getWebAutomation();
        for (int i = 0 ; i < webautomation.size() ; i++) {
            System.out.println("WebAutomation : "+webautomation.get(i).getCourseTitle());

        }
//        APi
        List<Api> api = courses.getApi();;
        for (int i = 0 ; i < api.size() ; i++) {
            System.out.println("Api : "+api.get(i).getCourseTitle());
        }

//        Mobile
        List<Mobile> mobile = courses.getMobile();
        for (int i = 0 ; i < mobile.size() ; i++) {
            System.out.println("Mobile : "+mobile.get(i).getCourseTitle());
        }
    }

    @AfterMethod
    public void tearDown() {
        RestAssured.reset();
    }
}
