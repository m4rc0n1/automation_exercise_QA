package tests.e2e;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.ChromeDriverConfig;
import utils.ConfigReader;

import javax.swing.*;
import java.time.Duration;
import java.util.HashMap;

public class PurchaseProductTest {
    WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    HomePage homePage = new HomePage();
    SignupLogin signupLoginPage = new SignupLogin();
    CartPage cartPage = new CartPage();
    Faker faker= new Faker();


    @BeforeMethod
    public void setup(){
        if(configReader.getProperty("browser").equals("chrome")){
           driver= ChromeDriverConfig.getConfiguratedChromeDriver(System.getProperty("user.dir")+ "/downloads");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(configReader.getProperty("url"));
        try{
            driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        }catch(NoSuchElementException err){
            System.out.println("Can not find Consent button");
        }
    }

    @Test
    public void purchaseProduct() {
        driver.findElement(By.xpath(homePage.signUpLoginBtnXpath)).click();
        HashMap<String, String> newUser = signupLoginPage.registerNewUser(driver);
        driver.findElement(By.xpath(homePage.homePageBtnXpath)).click();
        homePage.addProductdToCart(driver,1);
        driver.findElement(By.xpath(homePage.modalContinueBtnXpath)).click();
        homePage.addProductdToCart(driver,3);
        driver.findElement(By.xpath(homePage.modalViewCartBtnXpath)).click();
        driver.findElement(By.xpath(cartPage.proceedToCheckoutBtnXpath)).click();
        String totalAmount = cartPage.getTotalAmount(driver).getText();
        String[] amounts = totalAmount.split(" ");
        String amount = amounts[1];
        driver.findElement(By.xpath(cartPage.placeOrderBtnXpath)).click();
        driver.findElement(By.xpath(signupLoginPage.getLoginSignUpPageElementXpath("input", "name-on-card"))).sendKeys(faker.name().fullName());
        Actions actions= new Actions(driver);
        actions.sendKeys(Keys.TAB).sendKeys(faker.finance().creditCard())
                .sendKeys(Keys.TAB).sendKeys("123")
                .sendKeys(Keys.TAB).sendKeys("11")
                .sendKeys(Keys.TAB).sendKeys("2030").perform();
        driver.findElement(By.xpath(signupLoginPage.getLoginSignUpPageElementXpath("button", "pay-button"))).click();
        driver.findElement(By.xpath(cartPage.downloadInvoiceBtnXpath)).click();
    }

}










