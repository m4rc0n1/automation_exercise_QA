package tests.api.githubTest;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import pages.api.models.RepoModel;
import utils.ConfigReader;

import static com.google.common.base.Predicates.equalTo;

public class NegativeGithubTest {
    ConfigReader configReader = new ConfigReader();
    Gson gson = new Gson();
    String PTA = configReader.getProperty("PTA");
    String ApiUrl = configReader.getProperty("ApiUrl");
    String githubUsername = configReader.getProperty("GithubUsername");

    @Test
    public void deleteNotExisting() {
        RestAssured.baseURI = ApiUrl;
        RestAssured
                .given().auth().oauth2("invalid_token").contentType(ContentType.JSON)
                .when()
                .delete("/repos/" + githubUsername + "/testRepo3")
                .then().statusCode(401)
                .body("message", Matchers.equalTo("Bad credentials"));
    }

}





