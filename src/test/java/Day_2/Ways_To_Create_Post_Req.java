package Day_2;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class Ways_To_Create_Post_Req {
    String baseURL = "http://localhost:3000";

    //	1) Post Req Using HashMap (Suitable for small set of Data)
    @Test(enabled = true)
    void usingHashMap() {
        HashMap data = new HashMap();
        data.put("name", "Om");
        data.put("location", "Nallasopara");
        data.put("phone", "0987654321");
        data.put("courses", new String[]{"Java", "AWS"});
        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post(baseURL + "/students")
//					.jsonPath().getInt("id") To assign Id to the Variable
                .then()
                .statusCode(201)
                .body("name", equalTo("Om"))
                .body("location", equalTo("Nallasopara"))
                .body("phone", equalTo("0987654321"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("AWS"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    //	2) Post Req using org.json
    @Test(enabled = false)
    void usingOrgJsonLibrary() {
        JSONObject data = new JSONObject();        //Come from org.json
        data.put("name", "Om");
        data.put("location", "Nallasopara");
        data.put("phone", "0987654321");
        data.put("courses", new String[]{"Java", "AWS"});

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post(baseURL + "/students")
//					.jsonPath().getInt("id") To assign Id to the Variable
                .then()
                .statusCode(201)
                .body("name", equalTo("Om"))
                .body("location", equalTo("Nallasopara"))
                .body("phone", equalTo("0987654321"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("AWS"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    //	3) Post Req Using POJO classes
    @Test(enabled = false)
    void usingPOJOClass() {
        POJOClass_Data data = new POJOClass_Data();
        data.setName("Om");
        data.setLocation("Nallasopara");
        data.setPhone("0987654321");
        data.setCourses(new String[]{"Java", "AWS"});

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post(baseURL + "/students")
//					.jsonPath().getInt("id") To assign Id to the Variable
                .then()
                .statusCode(201)
                .body("name", equalTo("Om"))
                .body("location", equalTo("Nallasopara"))
                .body("phone", equalTo("0987654321"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("AWS"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    //	4) Post Req Using External JSON File
    @Test(enabled = false)
    void usingExternalJSON() throws FileNotFoundException {
        File fs = new File(System.getProperty("user.dir") + "\\src\\test\\java\\Day_2\\bodyData.json");
        FileReader fr = new FileReader(fs);        //java.io Package
        JSONTokener jt = new JSONTokener(fr);     //org.json Package
        JSONObject data = new JSONObject(jt);    //org.json Package

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post(baseURL + "/students")
//					.jsonPath().getInt("id") To assign Id to the Variable
                .then()
                .statusCode(201)
                .body("name", equalTo("Om"))
                .body("location", equalTo("Nallasopara"))
                .body("phone", equalTo("0987654321"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("AWS"))
                .header("Content-Type", "application/json; charset=utf-8")
                .log().all();
    }

    //	)Deleteing User
    @Test(priority = 1000, enabled = false)
    void deleteUser() {
        when()
                .delete(baseURL + "/students/5")
                .then()
                .statusCode(200)
                .log().all();
    }
}
