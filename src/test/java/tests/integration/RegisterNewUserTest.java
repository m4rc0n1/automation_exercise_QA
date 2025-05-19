package tests.integration;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;

import java.time.Duration;
import java.util.HashMap;

public class RegisterNewUserTest {
    WebDriver driver;
    String url = "https://automationexercise.com/";
    HomePage homePage = new HomePage();
    SignupLogin signupLoginPage = new SignupLogin();

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(url);
        try{
            driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        }catch(NoSuchElementException err){
            System.out.println("Can not find Consent button");
        }
    }
    @Test
    public  void registerNewUser(){
        driver.findElement(By.xpath(homePage.signUpLoginBtnXpath)).click();
        HashMap<String,String> newUser= signupLoginPage.registerNewUser(driver);
        signupLoginPage.getTitleEl(driver,1);
        driver.findElement(By.xpath(signupLoginPage.getLoginSignUpPageElementXpath("input","password"))).sendKeys();
        String actualAccountCreatedText = driver.findElement(By.xpath(signupLoginPage.accountCreatedTextXpath)).getText();
        String expectedAccountCreatedText = "Account Created!";
        Assert.assertEquals(actualAccountCreatedText, expectedAccountCreatedText);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
