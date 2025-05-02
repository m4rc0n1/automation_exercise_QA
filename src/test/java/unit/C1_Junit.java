package unit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class C1_Junit {
    WebDriver driver;
    String url = "https://google.com";

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
    }
    //Annotation
    @Test
    public void test01(){
        driver.get(url);
        WebElement searchBoxEl = driver.findElement(By.name("q"));
        // actualResult searchBoxEl.isDisplayed()
        //expectedResult false
        Assertions.assertTrue(searchBoxEl.isDisplayed());
        Assertions.assertEquals(true,searchBoxEl.isDisplayed());
    }
    @Test
    public void test02(){
        driver.get(url);
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        String checkBoxXpath="//input[@type='checkbox'][2]";
        WebElement checkBoxEl=driver.findElement(By.xpath(checkBoxXpath));
        Assertions.assertTrue(checkBoxEl.isSelected());
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @AfterAll
    public static void tearDownAll(){
        System.out.println("Unit tests have been finished");
    }
}
