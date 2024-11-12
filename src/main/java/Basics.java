import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) throws IOException {
        // Test for validating if Add Place API is working as expected.
        // Given - all input details. When - submit the API, http method. Then - validate the response.
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                //.body(Payload.AddPlace())
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\vvp\\IdeaProjects\\googleMapsRestAssuredProject\\src\\addPlace.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString(); // Extract the response in String format.

        System.out.println(response);
        JsonPath js = new JsonPath(response); // Convert the String into Json format, for parsing Json.
        String placeId = js.getString("place_id");

        System.out.println(placeId);

        // Add place -> Update Place with new address -> Get Place to validate if new address is present in response.
        String newAddress = "70 Summer walk, USA";
        given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
        .queryParam("place_id", placeId)
        .when().get("maps/api/place/get/json")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);

        //Cucumber Junit, TestNG
    }
}
