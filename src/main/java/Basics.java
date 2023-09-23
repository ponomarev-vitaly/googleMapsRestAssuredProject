import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Basics {
    public static void main(String[] args) {
        // Test for validating if Add Place API is working as expected.
        // Given - all input details. When - submit the API, http method. Then - validate the response.
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n" +
                "    \"location\": {\r\n" +
                "        \"lat\": -38.383494,\r\n" +
                "        \"lng\": 33.427362\r\n" +
                "    },\n" +
                "    \"accuracy\": 50,\r\n" +
                "    \"name\": \"Frontline house\",\r\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\r\n" +
                "    \"address\": \"29, side layout, cohen 09\",\r\n" +
                "    \"types\": [\r\n" +
                "        \"shoe park\",\r\n" +
                "        \"shop\"\r\n" +
                "    ],\r\n" +
                "    \"website\": \"https://rahulshettyacademy.com\",\r\n" +
                "    \"language\": \"French-IN\"\r\n" +
                "}")
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200);
    }
}
