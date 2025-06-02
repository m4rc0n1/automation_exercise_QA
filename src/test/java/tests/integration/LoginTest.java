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
import utils.TestListener;

import java.time.Duration;

@Listeners(TestListener.class)
//@Listeners(CustomReportListener.class)
public class LoginTest {

    WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    HomePage homePage = new HomePage();
    SignupLogin signupLogin = new SignupLogin();

    @DataProvider(name="LoginData")
    public Object[][] getData(){
        return new Object[][] {{"veli@gmail.com","2234#", false},{configReader.getProperty("email")
                ,configReader.getProperty("password"), true}};
    }

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        TestListener.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(configReader.getProperty("url"));
        try{
            driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        }catch(NoSuchElementException err){
            System.out.println("Can not find Consent button");
        }
    }
    @Test (dataProvider = "LoginData")
    public void loginUser(String email,String password, boolean isPositive){
        driver.findElement(By.xpath(homePage.signUpLoginBtnXpath)).click();
        signupLogin.login(email,password,driver);
        if(isPositive){
            boolean isLogoutDisplayed = driver.findElement(By.xpath(homePage.logoutBtnXpath)).isDisplayed();
            Assert.assertTrue(isLogoutDisplayed);
        }else {
            boolean isIncorrectPasswordTextDisplayed = driver.findElement(By.xpath(signupLogin.incorrectPasswordTextXpath)).isDisplayed();
            Assert.assertTrue(isIncorrectPasswordTextDisplayed);
        }
    }
    @Test
    public void testFailed(){
        Assert.assertTrue(false);
    }

    @AfterMethod
    public  void tearDown(){
        driver.quit();
    }
}
