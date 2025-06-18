package tests.api.githubTest;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.api.models.RepoModel;
import utils.ConfigReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import static org.hamcrest.Matchers.equalTo;

public class DataDrivenGithubTest {
    ConfigReader configReader = new ConfigReader();
    Gson gson = new Gson();
    String PTA = configReader.getProperty("PTA");
    String ApiUrl = configReader.getProperty("ApiUrl");
    String githubUsername = configReader.getProperty("GithubUsername");

    @DataProvider(name = "gitHub")
    public Object[][] repoData(){
        return new Object[][] {{"repo1", "description1", false},{"repo2", "description2", false}};
    }
    @Test(dataProvider = "gitHub")
    public void createNewRepoDataProvider(String name, String description, boolean isPrivate){
        RestAssured.baseURI = ApiUrl;
        RepoModel repoModel = new RepoModel(name, description, isPrivate);
        String jsonPayload = gson.toJson(repoModel);
        RestAssured
                .given().auth().oauth2(PTA).contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/user/repos")
                .then().statusCode(201)
                .body("name", equalTo(name))
                .body("description", equalTo(description))
                .body("private", equalTo(false));
    }

    @Test
    public void createNewRepoCsv() throws IOException {
        RestAssured.baseURI = ApiUrl;
        FileReader fr= new FileReader("files/repo_data.csv");
        CSVParser csvParser = new CSVParser(fr, CSVFormat.DEFAULT.withHeader());
        Iterator<CSVRecord> iterator = csvParser.iterator();
        while (iterator.hasNext()){
            CSVRecord record = iterator.next();
            //extract data
            String name = record.get("name");
            String description = record.get("description");
            boolean isPrivate = Boolean.parseBoolean(record.get("isPrivate"));
            RepoModel repoModel = new RepoModel(name, description, isPrivate);
            String jsonPayload = gson.toJson(repoModel);
            RestAssured
                    .given().auth().oauth2(PTA).contentType(ContentType.JSON)
                    .body(jsonPayload)
                    .when()
                    .post("/user/repos")
                    .then().statusCode(201)
                    .body("name", equalTo(name))
                    .body("description", equalTo(description))
                    .body("private", equalTo(isPrivate));
        }
    }
}








