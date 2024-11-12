package demo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class BugTest {
    public static void main(String[] args) {
        RestAssured.baseURI="https://mrgraymatter-team.atlassian.net";
        String createIssueResponse = given()
                .header("Content-Type","application/json")
                .header("Authorization", "Basic djY2NzYxMzBAZ21haWwuY29tOkFUQVRUM3hGZkdGME9hb0Jjc1JTSEVYRXRzRTh6MUIxR0hmc3p2RDRsUUpyV3I3NzBJVFdnY0pvb0pHWVlFR2dIZk5aU01yMjI0RHQ5TVE3aVNBWGVUSDFwa1hhRFY4U2V0cW5XZXlUX0QyTHMxdGVVenJ5REpjYW1PNzRCYU1WbVdfektBUEpyN0Y0YmJkN2xKM19wU2VSWFhIQmpvbkJ4TlQ1TFB3bGpMOElWTUIxcW56N1hOST0yNjkwMUEzRg==")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "        \"project\": {\n" +
                        "            \"key\": \"SCRUM\"\n" +
                        "        },\n" +
                        "        \"summary\": \"Links are not working - automation\",\n" +
                        "        \"issuetype\": {\n" +
                        "            \"name\": \"Bug\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "}")
                .log().all()
                .post("rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);


    }

}
