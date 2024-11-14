import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class serializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response res = given().queryParam("key", "qaclick123")
                .body("")
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response();

        String responseString = res.asString();
        System.out.println(responseString);

    }
}
