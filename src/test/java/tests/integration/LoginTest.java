package tests.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.ConfigReader;
import utils.CustomReportListener;
import utils.RetryAnalyzer;
import utils.TestListener;
import utils.setup.BaseTest;

import java.time.Duration;

@Listeners(TestListener.class)
//@Listeners(CustomReportListener.class)
    public class LoginTest extends BaseTest {
    @DataProvider(name="LoginData")
    public Object[][] getData(){
        return new Object[][] {{"veli@gmail.com","2234#", false},{configReader.getProperty("email")
                ,configReader.getProperty("password"), true}};
    }
    @Test (dataProvider = "LoginData", groups ={"smoke"} )
    public void loginUser(String email,String password, boolean isPositive){
        driver.findElement(By.xpath(homePage.signUpLoginBtnXpath)).click();
        signupLoginPage.login(email,password,driver);
        if(isPositive){
            boolean isLogoutDisplayed = driver.findElement(By.xpath(homePage.logoutBtnXpath)).isDisplayed();
            Assert.assertTrue(isLogoutDisplayed);
        }else {
            boolean isIncorrectPasswordTextDisplayed = driver.findElement(By.xpath(signupLoginPage.incorrectPasswordTextXpath)).isDisplayed();
            Assert.assertTrue(isIncorrectPasswordTextDisplayed);
        }
    }
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"skipped"})
    public void testFailed(){
        Assert.assertTrue(false);
    }
}
