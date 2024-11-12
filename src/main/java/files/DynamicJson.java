package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payload.Addbook(isbn,aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @Test(dataProvider = "BooksData")
    public void deleteBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String deleteResponse = given().header("Content-Type", "application/json")
                .body(Payload.Deletebook(isbn,aisle))
                .when()
                .delete("/Library/DeleteBook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println(deleteResponse.toString());
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]{{"ojfwty", "9363"}, {"coffee", "4235"}, {"akmfet", "533"}};
    }

}
