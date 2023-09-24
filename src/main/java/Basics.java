import files.Payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {
        // Test for validating if Add Place API is working as expected.
        // Given - all input details. When - submit the API, http method. Then - validate the response.
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString(); // Extract the response in String format.

        System.out.println(response);

        // Add place -> Update Place with new address -> Get Place to validate if new address is present in response.
    }
}
