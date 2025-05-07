package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public String signUpLoginBtnXpath="//a[text()=' Signup / Login']";
    public String modalViewCartBtnXpath="//u[text()='View Cart']";
    public String modalContinueBtnXpath= "//button[text()='Continue Shopping']";
    public String addProductdToCart(WebDriver driver,int productId){
        Actions actions=new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@data-product-id='" + productId + "'])[1]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
        actions.moveToElement(product).perform();
        product.click();
        return driver.findElement(By.xpath("(//div[@class='productinfo text-center'])["+productId+"]//p")).getText();
    }
}
