package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    public String productListNameXpath="(//tbody//tr)//td//h4//a";
    public String deleteProductBtnsXpath="//tbody//tr/td//a//i";
    public String cartIsEmptyTextXpath= "//b[text()='Cart is empty!']";

    public void deleteAllProducts(WebDriver driver){
        List<WebElement> deleteAllProductsEl= driver.findElements(By.xpath(deleteProductBtnsXpath));
        for(WebElement deleteProductBtn:deleteAllProductsEl){
            deleteProductBtn.click();
        }
    }
    public void deleteProduct(WebDriver driver, int productId){
        driver.findElement(By.xpath(("("+ deleteProductBtnsXpath+")["+productId+"]"))).click();
    }
}

