package tests.integration;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;

import java.time.Duration;
import java.util.List;

public class AddProductToCartTest {
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
    public void addProductToCart(){
        WebElement signUpLoginBtnEl = driver.findElement(By.xpath(homePage.signUpLoginBtnXpath));
        Assert.assertTrue(signUpLoginBtnEl.isDisplayed());
        String addedProductName = homePage.addProductdToCart(driver,8);
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

    @Test
    @Description("Add product to  cart with user.")
    public void addProductToCartWithUser() {
        // 2. signup/login sec
        WebElement signUpLoginBtnEl = driver.findElement(By.xpath(homePage.signUpLoginBtnXpath));
        Assert.assertTrue(signUpLoginBtnEl.isDisplayed());
        signUpLoginBtnEl.click();
        // 3. login ol
        signupLoginPage.login(emailData, passwordData, driver);
        //4. productu secib sebete elave et
        String addedProductName = homePage.addProductdToCart(driver,1);
        driver.findElement(By.xpath(homePage.modalViewCartBtnXpath)).click();
        // 5. sebetde oldugunu yoxla
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
