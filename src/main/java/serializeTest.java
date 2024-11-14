import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class serializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        given().queryParam("key", "qaclick123")
                .body(arg0)
                .when().post("maps/api/place/add/json")

        ;

    }
}
