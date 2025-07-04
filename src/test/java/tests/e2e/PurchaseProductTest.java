package tests.e2e;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.ChromeDriverConfig;
import utils.ConfigReader;
import utils.WaitFileReader;
import utils.setup.BaseTest;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class PurchaseProductTest  extends BaseTest {
    @Test(groups = {"regression"})
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
        String expectedResult = "Hi "+ newUser.get("firstName")+ " " + newUser.get("lastName")+", Your total purchase amount is "+ amount+". Thank you";
        Assert.assertEquals(firstLineText, expectedResult);
    }

    @AfterMethod
    @Override
    public void tearDown(){
        driver.quit();
        File downloadDir = new File(System.getProperty("user.home") + "/Downloads");
        File invoiceFile = new File(downloadDir, "invoice.txt");
        if(invoiceFile.exists()) invoiceFile.delete();
    }
}










