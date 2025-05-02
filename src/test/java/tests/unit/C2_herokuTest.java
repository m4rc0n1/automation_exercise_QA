package tests.unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Screenshot;
import utils.ScreenshotOnFailureOrPassed;

import java.time.Duration;

@ExtendWith(ScreenshotOnFailureOrPassed.class)
public class C2_herokuTest {
    WebDriver driver;
    @BeforeAll
    public static void initalize(){
        System.out.println("Unit tests have been started");
    }

    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        ScreenshotOnFailureOrPassed.setDriver(driver);
    }

    @Test
    @Disabled
    public void addBtn(){
       driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
       WebElement btnEl= driver.findElement(By.xpath("//button"));
       btnEl.click();
       WebElement btn2El = driver.findElement(By.xpath("(//button)[2]"));
       boolean isBtn2Visible = btn2El.isDisplayed();
       Assertions.assertTrue(isBtn2Visible);
       btn2El.click();
       try{
            driver.findElement(By.xpath("(//button)[2]"));
       }catch( NoSuchElementException err ){
           isBtn2Visible=false;
       }
       Assertions.assertFalse(isBtn2Visible);
    }

    @ParameterizedTest
    @Disabled
    @ValueSource(strings ={"https://amazon.com","https://google.com"})
    public void checkTitle(String url){
        driver.get(url);
        driver.getTitle();
    }
    @Test
    @Disabled
    public void exercise(){
      driver.get("https://automationexercise.com/view_cart");
      driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
          WebElement hereBtnEl  = driver.findElement(By.linkText("here"));
          hereBtnEl.click();
          String productUrl= driver.getCurrentUrl();
//  Manually take screenshot
//  Screenshot.takeScreenshot(driver,"View cart btn");
          Assertions.assertEquals("https://automationexercise.com/products",productUrl);
    }

    @Test

    public void exercise2(){
        driver.get("https://automationexercise.com");
        driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        WebElement addToCartBtnEl=driver.findElement(By.xpath("(//a[@data-product-id='8'])[1]"));
        addToCartBtnEl.click();
        WebElement viewCartBtnEl=driver.findElement(By.xpath("//u[text()='View Cart']"));
        viewCartBtnEl.click();
        WebElement proceedToCheckoutBtnEl=driver.findElement(By.linkText("Proceed To Checkout"));
        Assertions.assertTrue(proceedToCheckoutBtnEl.isDisplayed());
    }

    @AfterEach
    public void tearDown(){

    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Unit tests have been finished");
    }
}
