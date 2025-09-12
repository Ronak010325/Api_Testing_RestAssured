package Day_5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class fileUploadDownload {
	@Test(enabled=true)
	public void testSingleFile() {
		File myFile = new File("C:\\Users\\K.K\\OneDrive\\Desktop\\command.txt");
		given()
			.multiPart("file", myFile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/upload")
		.then()
			.statusCode(200)
			.body("message", equalTo("File uploaded successfully!"))
			.log().all();
	}
	
	@Test(enabled=false)
	public void testMultipleFiles() {
		File myFile1 = new File("C:\\Users\\K.K\\OneDrive\\Desktop\\command.txt");
		File myFile2 = new File("C:\\Users\\K.K\\OneDrive\\Desktop\\name.txt");		
		given()
			.multiPart("files", myFile1)
			.multiPart("files", myFile2)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/upload-multiple")
		.then()
			.statusCode(200)
			.body("message", equalTo("2 files uploaded successfully!"))
			.log().all();
	}
	
	@Test(enabled=false)
	public void testDownload() {
		when()
			.get("http://localhost:3000/download/1754924274032-command.txt")
		.then()
			.statusCode(200)
			.log().all();
	}
	
}
