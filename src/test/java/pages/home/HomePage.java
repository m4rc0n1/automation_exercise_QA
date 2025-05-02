package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public String signUpLoginBtnXpath="//a[text()=' Signup / Login']";
    public String modalViewCartBtnXpath="//u[text()='View Cart']";
    public String addProductdToCart(WebDriver driver,int productId){
        driver.findElement(By.xpath("(//a[@data-product-id='"+productId+"'])[1]")).click();
        return driver.findElement(By.xpath("(//div[@class='productinfo text-center'])["+productId+"]//p")).getText();
    }
}
