import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace p = new AddPlace();

        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setLanguage("French-IN");

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        Response res = given().queryParam("key", "qaclick123")
                .body("")
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response();

        String responseString = res.asString();
        System.out.println(responseString);

    }
}
