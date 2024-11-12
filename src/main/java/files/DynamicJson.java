package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test
    public void addBook(){
        RestAssured.baseURI="http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payload.Addbook())
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);


    }

}
