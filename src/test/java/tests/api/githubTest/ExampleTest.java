package tests.api.githubTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class ExampleTest {
    ConfigReader configReader = new ConfigReader();
    String ApiUrl = configReader.getProperty("ApiUrl");

    @Test
    public  void getUserInfo(){
        RestAssured.baseURI = ApiUrl;
        /*
        1.given
        2.when
        3.then
         */
        Response response = RestAssured
                .given()
                .when()
                .get("/users/octocat")
                .then()
                .extract()
                .response();
        Assert.assertEquals(response.statusCode(),200);
    }
}