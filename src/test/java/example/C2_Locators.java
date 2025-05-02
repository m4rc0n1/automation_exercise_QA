package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C2_Locators {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("https://amazon.com");
        String searchBoxXpath= "//input[@id='twotabsearchtextbox']";
        WebElement searchBoxEl= driver.findElement(By.xpath(searchBoxXpath));
        // className . id #
//        driver.findElement(By.cssSelector(".nav-input"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        String dismissBtnXpath="//input[@data-action-type='DISMISS']";
        driver.findElement(By.xpath(dismissBtnXpath)).click();
        searchBoxEl.sendKeys("laptop", Keys.ENTER);
        driver.quit();
    }
}
