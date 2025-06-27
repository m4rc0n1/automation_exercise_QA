//package tests.unit;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class C3_Qizqayit_Error_Test {
//    WebDriver driver;
//    String url = "https://automationexercise.com/ ";
//    @BeforeEach
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }
//    @Test
//    @Disabled
//    public  void addToCart() {
//        driver.get(url);
//
//        WebElement pro = driver.findElement(By.xpath("//a[@href='/products']"));
//        pro.click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement wiewPro = driver.findElement(By.xpath("(//i[@class='fa fa-plus-square'])[4]"));
//        wait.until(ExpectedConditions.elementToBeClickable(wiewPro));
//        wiewPro.click();
//
//
//
//        WebElement addCt = driver.findElement(By.xpath("button[type='button']"));
//        addCt.click();
//        boolean isaddCt = addCt.isDisplayed();
//        Assertions.assertTrue(isaddCt);
//
//
//        WebElement coSh = driver.findElement(By.xpath("//button[@class='btn btn-success close-modal btn-block'"));
//        boolean iscoShVisible = coSh.isDisplayed();
//        Assertions.assertTrue(iscoShVisible);
//    }
//}
