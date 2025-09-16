package Library_Rahul_Shetty_API;

import ExcelUtitlies.Excel_Utils;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataDriven_UsingExcel {
    String base_URL = "http://216.10.245.166";
    @DataProvider(name = "booksDetails")
    public Object[][] data() throws IOException {
        Excel_Utils exc = new Excel_Utils();
        String path = System.getProperty("user.dir")+"//testData//Data_for_API.xlsx";
        int rowCount = exc.getRowCount(path, "Book_Details");
        int cellCount = exc.getCellCount(path, "Book_Details");
        String[][] data = new String[rowCount-1][cellCount];
        for (int i = 1 ; i < rowCount ; i++) {
            for (int j = 0 ; j < cellCount ; j++) {
                data[i-1][j] = exc.getValue(path, "Book_Details", i, j);
            }
        }
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
