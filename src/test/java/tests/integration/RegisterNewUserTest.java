package tests.integration;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    CartPage cartPage = new CartPage();
    SignupLogin signupLoginPage = new SignupLogin();
    String emailData="testercourse@gmail.com";
    String passwordData="Test123!";

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


        //sayta get
        //loginsignup seyfesine kec
        driver.findElement(By.xpath(homePage.signUpLoginBtnXpath)).click();
        HashMap<String,String> newUser= signupLoginPage.registerNewUser(driver);
        System.out.println(newUser);
        //username yaz
//        driver.findElement(By.xpath(sign));
        //email yaz

        //signup duymesine tikla




    }


}
