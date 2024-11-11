import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static java.lang.Integer.parseInt;

public class SumValidation {
    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int count = js.getInt("courses.size()");

        for(int i=0;i<count;i++){
            String priceStr = js.getString("courses["+i+"].price");
            int price = parseInt(priceStr);
            int copies = js.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
        }

    }


}
