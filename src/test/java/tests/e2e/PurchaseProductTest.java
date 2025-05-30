package tests.e2e;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.ChromeDriverConfig;
import utils.ConfigReader;
import utils.WaitFileReader;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class PurchaseProductTest {
    WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    HomePage homePage = new HomePage();
    SignupLogin signupLoginPage = new SignupLogin();
    CartPage cartPage = new CartPage();
    Faker faker= new Faker();
    String downloadPath = System.getProperty("user.dir")+ "\\downloads\\";


    @BeforeMethod
    public void setup(){
        if(configReader.getProperty("browser").equals("chrome")){
           driver= ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
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
        Actions actions= new Actions(driver);
        String firstLineText = null;
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
        WebElement placeOrderBtnEl = driver.findElement(By.xpath(cartPage.placeOrderBtnXpath));
        actions.moveToElement(placeOrderBtnEl).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",placeOrderBtnEl);
        placeOrderBtnEl.click();
        driver.findElement(By.xpath(signupLoginPage.getLoginSignUpPageElementXpath("input", "name-on-card"))).sendKeys(faker.name().fullName());
        actions.sendKeys(Keys.TAB).sendKeys(faker.finance().creditCard())
                .sendKeys(Keys.TAB).sendKeys("123")
                .sendKeys(Keys.TAB).sendKeys("11")
                .sendKeys(Keys.TAB).sendKeys("2030").perform();
        WebElement payButtonEl =  driver.findElement(By.xpath(signupLoginPage.getLoginSignUpPageElementXpath("button", "pay-button")));
        js.executeScript("arguments[0].scrollIntoView(true)", payButtonEl);
        payButtonEl.click();
        driver.findElement(By.xpath(cartPage.downloadInvoiceBtnXpath)).click();
        File downloadedFile = new File(downloadPath + "invoice.txt");
        WaitFileReader.waitForFileToBeAvailable(downloadedFile, 15);
        try(BufferedReader reader = new BufferedReader(new FileReader(downloadedFile))){
            firstLineText = reader.readLine();
        }catch (IOException err){
            System.out.println("Can not read file: invoice.txt");
            err.printStackTrace();
        }
        System.out.println(firstLineText);
    }

}










