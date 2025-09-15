package RahulShetty;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class TestCase_JSON_Rahul_Sheety {
    @Test
    public void jsonPathSection () {
        JsonPath js = new JsonPath(Payload.addAddress());

//        Print No of courses returned by API
        int total_courses = js.getInt("courses.size()");
        System.out.println(total_courses);

//        Print Purchase Amount
        int purchase_amount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchase_amount);

//        Print Title of the first course
        String title = js.getString("courses[0].title");
        System.out.println(title);

//        Print All course titles and their respective Prices
        for (int i = 0 ; i < total_courses ; i++) {
            System.out.println("Course Title : "+js.getString("courses["+i+"].title"));
            System.out.println("Course Price : "+js.getString("courses["+i+"].price"));
        }

//        Print no of copies sold by RPA Course
        for (int i = 0 ; i < total_courses ; i++) {
            if(js.getString("courses["+i+"].title").equals("RPA")) {
                System.out.println("No. of Copies sold by RPA : "+js.getInt("courses["+i+"].copies"));
            }
        }

//        Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i = 0 ; i < total_courses ; i++) {
            int purchase_amt = js.getInt("courses["+i+"].price");
            sum += purchase_amt;
        }

        if (sum == purchase_amount) {
            System.out.println("Purchase amount : "+purchase_amount);
            System.out.println("Total Course amount : "+sum);
            System.out.println("It is Equal");
        } else {
            System.out.println("Purchase amount : "+purchase_amount);
            System.out.println("Total Course amount : "+sum);
            System.out.println("It is Not Equal");
        }
    }
}
