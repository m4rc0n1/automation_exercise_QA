package tests.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.setup.BaseTest;

import java.time.Duration;

public class DeleteAllProductsTest  extends BaseTest {
    @Test (groups = {"regression"})
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartPage.cartIsEmptyTextXpath)));
        String actualResult= driver.findElement(By.xpath(cartPage.cartIsEmptyTextXpath)).getText();
        String expectedResult = "Cart is empty!";
        Assert.assertEquals(actualResult, expectedResult);
    }
}
