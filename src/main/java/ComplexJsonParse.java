import files.Payload;
import groovy.json.JsonOutput;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());

        // Print number of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);
        // Print Purchase amount.
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        // Print the title of the first course
        String titleFirstCourse=js.get("courses[0].title");
        System.out.println(titleFirstCourse);
    }
}
