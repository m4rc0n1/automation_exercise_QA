package tests.api.githubTest;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pages.api.models.RepoModel;
import utils.ConfigReader;

public class GithubTest {
    ConfigReader configReader = new ConfigReader();
    String PTA = configReader.getProperty("PTA");
    String ApiUrl = configReader.getProperty("ApiUrl");
    @Test
    public void createNewRepo() {
        RestAssured.baseURI = ApiUrl;
        RepoModel repoModel = new RepoModel("testRepo1", "testRepo1Description", false);
        Gson gson= new Gson();
        //Gson: converting Java Object to Json/Xml format -> serialization
        //Opposite: deserialization
        String jsonPayload = gson.toJson(repoModel);
        RestAssured
                .given().auth().oauth2(PTA).contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/user/repos")
                .then().statusCode(201);
    }
}