package tests.api.githubTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class NegativeGithubTest {
    ConfigReader configReader = new ConfigReader();
    String PTA = configReader.getProperty("PTA");
    String ApiUrl = configReader.getProperty("ApiUrl");
    String githubUsername = configReader.getProperty("GithubUsername");

    @Test
    public void deleteRepoInvalidToken(){
        RestAssured.baseURI = ApiUrl;
        RestAssured
                .given().auth().oauth2("invalid_token").contentType(ContentType.JSON)
                .when()
                .delete("/repos/" + githubUsername + "/testRepo3")
                .then().statusCode(401)
                .body("message", Matchers.equalTo("Bad credentials"));
    }
    @Test
    public void deleteNonExistingRepo(){
        RestAssured.baseURI = ApiUrl;
        RestAssured
                .given().auth().oauth2(PTA).contentType(ContentType.JSON)
                .when()
                .delete("repos/Gizgayit/repoModel000")
                .then().statusCode(404)
                .body("message", Matchers.equalTo("Bad credentials"));
    }
}





