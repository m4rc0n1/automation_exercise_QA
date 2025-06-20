package tests.api.githubTest;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pages.api.models.RepoModel;
import utils.ConfigReader;
import static org.hamcrest.Matchers.equalTo;

public class GithubTest {
    ConfigReader configReader = new ConfigReader();
    Gson gson = new Gson();
    String PTA = configReader.getProperty("PTA");
    String ApiUrl = configReader.getProperty("ApiUrl");
    String githubUsername = configReader.getProperty("GithubUsername");

    @Test
    public void createNewRepo() {
        RestAssured.baseURI = ApiUrl;
        RepoModel repoModel = new RepoModel("testRepo1", "testRepo1Description", false);
        //Gson: converting Java Object to Json/Xml format -> serialization
        //Opposite: deserialization
        String jsonPayload = gson.toJson(repoModel);
        RestAssured
                .given().auth().oauth2(PTA).contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/user/repos")
                .then().statusCode(201)
                .body("name", equalTo("testRepo1"))
                .body("description", equalTo("testRepo1Description"))
                .body("private", equalTo(false));
    }

    @Test(priority = 2, dependsOnMethods = "updateRepo")
    public void deleteRepo() throws InterruptedException {
        Thread.sleep(3000);
        RestAssured.baseURI = ApiUrl;
        RestAssured
                .given().auth().oauth2(PTA)
                .when()
                .delete("/repos/"+githubUsername+"/testRepo2")
                .then().statusCode(204);
    }

    @Test(priority = 1, dependsOnMethods = "createNewRepo")
    public void updateRepo()  {
        RestAssured.baseURI = ApiUrl;
        RepoModel repoModel = new RepoModel("testRepo2", "testRepo2Description", false);
        String jsonPayload = gson.toJson(repoModel);
        RestAssured
                .given().auth().oauth2(PTA).contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .patch("/repos/"+githubUsername+"/testRepo1")
                .then().statusCode(200)
                .body("name", equalTo("testRepo2"))
                .body("description", equalTo("testRepo2Description"))
                .body("private", equalTo(false));
    }

}

