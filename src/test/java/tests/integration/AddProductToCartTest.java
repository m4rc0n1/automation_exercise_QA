package tests.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;

import java.time.Duration;
import java.util.List;

public class AddProductToCartTest {
    WebDriver driver;
    String url = "https://automationexercise.com/";
    HomePage homePage = new HomePage();
    CartPage cartPage = new CartPage();
    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void addProductToCart(){
        driver.get(url);
        driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        WebElement signUpLoginBtnEl = driver.findElement(By.xpath(homePage.signUpLoginBtnXpath));
        Assert.assertTrue(signUpLoginBtnEl.isDisplayed());
        String addedProductName = homePage.addProductdToCart(driver,5);
        driver.findElement(By.xpath(homePage.modalViewCartBtnXpath)).click();
        List<WebElement> productsListEl=driver.findElements(By.xpath(cartPage.productListNameXpath));
        for(WebElement product:productsListEl){
           String productName= product.getText();
            System.out.println(addedProductName);
            System.out.println(productName);
           if(productName.equals(addedProductName)){
               Assert.assertTrue(true);
           }
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
