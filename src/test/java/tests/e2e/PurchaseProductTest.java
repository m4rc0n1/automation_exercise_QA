package tests.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.ConfigReader;

import java.time.Duration;
import java.util.HashMap;

public class PurchaseProductTest {
    WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    HomePage homePage = new HomePage();
    SignupLogin signupLoginPage = new SignupLogin();


    @BeforeMethod
    public void setup(){
        if(configReader.getProperty("browser").equals("chrome")){
            WebDriverManager.chromedriver().setup();}
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(configReader.getProperty("url"));
        try{
            driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        }catch(NoSuchElementException err){
            System.out.println("Can not find Consent button");
        }
    }
    //register
    //addproduct 2 minimum
    // odenis
    //invoice dovload
    @Test
    public void purchaseProduct() {
        driver.findElement(By.xpath(homePage.signUpLoginBtnXpath)).click();
        HashMap<String, String> newUser = signupLoginPage.registerNewUser(driver);
        driver.findElement(By.xpath(homePage.homePageBtnXpath)).click();

    }

}










