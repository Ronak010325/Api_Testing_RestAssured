package Library_Rahul_Shetty_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import RahulShetty.Payload;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testing_Library_API {
    String base_URL = "http://216.10.245.166";
    @Test(priority = 0)
    public void addBook() {
        HashMap map = new HashMap();
        map.put("name","learn Appium Automation with Java");
        map.put("isbn","bcd");
        map.put("aisle","010325");
        map.put("author","Ronak Yadav");
        Response res = given()
                .header("Content-Type","application/json")
                .body(map)
                .when()
                .post(base_URL+"/Library/Addbook.php");
        JSONObject jsObj = new JSONObject(res.asString());
        Assert.assertEquals(jsObj.get("Msg"),"successfully added");
//      You can directly do this using body() after then() but if you want to store the res
//      and then perform validation then you can go with this method
    }

    @Test(priority = 1, dependsOnMethods = {"addBook"}, enabled = false)
    public void getBook() {
        given()
                .queryParam("AuthorName","Ronak Yadav")
                .when()
                .get(base_URL+"/Library/GetBook.php")
                .then()
                .statusCode(200)
                .body("[1].aisle",equalTo("10325"))
                .log().all();
    }

    @Test(priority = 2, dependsOnMethods = {"addBook"}, enabled = false)
    public void getBookById() {
        given()
                .queryParam("ID","bcd010325")
                .when()
                .get(base_URL+"/Library/GetBook.php")
                .then()
                .statusCode(200)
                .body("[0].author",equalTo("Ronak Yadav"))
                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"addBook"}, alwaysRun = true)
    public void deleteBook() {
        HashMap map = new HashMap();
        map.put("ID","bcd010325");
        given()
                .body(map)
                .when()
                .post(base_URL+"/Library/DeleteBook.php")
                .then()
                .statusCode(200)
                .body("msg",equalTo("book is successfully deleted"))
                .log().all();
    }
}
