package Library_Rahul_Shetty_API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import RahulShetty.Payload;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Datadriven_Testing {
    String base_URL = "http://216.10.245.166";
    @DataProvider(name = "booksDetails")
    public Object[][] data() {
        String[][] data = {
                {"50 Shades", "abcd", "0978", "Ronak Kumar"},
                {"Learn Selenium", "tysh", "4590", "Gkings"},
                {"FriendShip is the true prize", "fjri", "4434", "Ronak Kumar"}
        };
        return data;
    }

    @Test(priority = 0, dataProvider = "booksDetails")
    public void addBook(String name, String isbn, String aisle, String author) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name",name);
        map.put("isbn",isbn);
        map.put("aisle",aisle);
        map.put("author",author);
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

    @Test(priority = 1, dataProvider = "booksDetails", dependsOnMethods = {"addBook"}, enabled = true)
    public void getBook(String name, String isbn, String aisle, String author) {
        System.out.println("Books Written By : "+author);
        given()
                .queryParam("AuthorName",author)
                .when()
                .get(base_URL+"/Library/GetBook.php")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 2, dataProvider = "booksDetails", dependsOnMethods = {"addBook"}, enabled = true)
    public void getBookById(String name, String isbn, String aisle, String author) {
        given()
                .queryParam("ID",isbn+aisle)
                .when()
                .get(base_URL+"/Library/GetBook.php")
                .then()
                .statusCode(200)
                .body("[0].author",equalTo(author))
                .log().all();
    }

    @Test(priority = 3, dataProvider = "booksDetails", dependsOnMethods = {"addBook"}, alwaysRun = true)
    public void deleteBook(String name, String isbn, String aisle, String author) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ID",isbn+aisle);
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
