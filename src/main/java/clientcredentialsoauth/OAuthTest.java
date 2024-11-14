package clientcredentialsoauth;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTest {
    public static void main(String[] args) {

    String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

    String response = given()
                .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

    System.out.println(response);

    JsonPath js = new JsonPath(response);
    String accessToken = js.getString("access_token");

    String response2String = given()
                .queryParams("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

    System.out.println(response2String);

    // Checking for the "error" field
    JsonPath jsonResponse = new JsonPath(response2String);
    if (jsonResponse.get("error") != null) {
        System.out.println("Error in response: " + jsonResponse.getString("error"));
    } else {
    // Deserialization in the GetCourse object
        GetCourse course = jsonResponse.getObject("", GetCourse.class);
        System.out.println(course.getlinkedIn());
        System.out.println(course.getInstructor());

        System.out.println(course.getCourses().getApi().get(1).getCourseTitle());

        List<Api> apiCourse = course.getCourses().getApi();
        for(int i=0;i<apiCourse.size();i++){
            if(apiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apiCourse.get(i).getPrice());
            }
        }

        ArrayList<String> a = new ArrayList<String>();
        List<WebAutomation> webAutomationCourse = course.getCourses().getWebAutomation();

        for(int n=0;n<webAutomationCourse.size();n++){
            // System.out.println(webAutomationCourse.get(n).getCourseTitle());
            a.add(webAutomationCourse.get(n).getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedList));


    }

    }

}
