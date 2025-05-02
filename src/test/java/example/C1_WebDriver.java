package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class C1_WebDriver {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("https://amazon.com");
        driver.get("https://google.com");
        String actualResult=driver.getTitle();
        String expectedResult = "Amazon.com. Spend less. Smile more.";
//        if(actualResult.equals(expectedResult)){
//            System.out.println("Test passed");
//        }else{
//            System.out.println("Test failed");
//        }
        driver.getCurrentUrl();
        driver.getPageSource();
        driver.getWindowHandle();
        driver.navigate().to("https://google.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        System.out.println(driver.getWindowHandles());
        driver.close(); // tabi baglayir
        driver.quit(); // browseri baglayir
 }
}
