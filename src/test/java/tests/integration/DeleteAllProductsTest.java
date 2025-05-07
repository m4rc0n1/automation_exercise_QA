package tests.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;

import java.time.Duration;

public class DeleteAllProductsTest {
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
    @Description ("Delete all products from the card")
    public void deleteAllProducts(){
        //1 sayti ac, 2. concenti qebul ele
        //3.login ol
        WebElement signUpEl = driver.findElement(By.xpath(homePage.signUpLoginBtnXpath));
        signUpEl.click();
        signupLoginPage.login(emailData, passwordData, driver);
        //4.producti sec
        homePage.addProductdToCart(driver, 1);
        //5.continue shopping
        driver.findElement(By.xpath(homePage.modalContinueBtnXpath)).click();
        //6.yeni product sec
        homePage.addProductdToCart(driver, 2);
        //7.view card clik
        driver.findElement(By.xpath(homePage.modalViewCartBtnXpath)).click();
        //8.her bir productu sil
        cartPage.deleteAllProducts(driver);

    }



}
