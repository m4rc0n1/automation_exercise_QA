package tests.api.githubTest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class GithubTest {
    ConfigReader configReader = new ConfigReader();
    String PTA = configReader.getProperty("PTA");
    String ApiUrl = configReader.getProperty("ApiUrl");
    @Test
    public void createNewRepo() {
        RestAssured.baseURI = ApiUrl;

    }
}