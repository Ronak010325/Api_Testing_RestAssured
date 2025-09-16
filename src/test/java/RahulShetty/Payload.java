package RahulShetty;

public class Payload {
    public static String addAddress() {
        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 910,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String addBook(String isbn, String aisle) {
//        return "{ name\":\"learn Appium Automation with Java\",\""+isbn+"\":\"bcd\",\""+aisle+"\":\"010325\",\"author\":\"Ronak Yadav\"}";
        return "{\n" +
                "\t\"name\":\"learn Appium Automation with Java\",\n" +
                "    \"isbn\":\""+isbn+"\",\n" +
                "    \"aisle\":\""+aisle+"\",\n" +
                "    \"author\":\"Ronak Yadav\"\n" +
                "}";
    }

    public static String addBook(String bookName, String isbn, String aisle, String author) {
//        return "{ name\":\"learn Appium Automation with Java\",\""+isbn+"\":\"bcd\",\""+aisle+"\":\"010325\",\"author\":\"Ronak Yadav\"}";
        return "{\n" +
                "\t\"name\":\""+bookName+"\",\n" +
                "    \"isbn\":\""+isbn+"\",\n" +
                "    \"aisle\":\""+aisle+"\",\n" +
                "    \"author\":\""+author+"\"\n" +
                "}";
    }
}
