package pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    public String productListNameXpath="(//tbody//tr)//td//h4//a";
    public String deleteProductBtnsXpath="//tbody//tr/td//a//i";
    public String cartIsEmptyTextXpath= "//b[text()='Cart is empty!']";
    public String proceedToCheckoutBtnXpath = "//a[text()='Proceed To Checkout']";
    public String placeOrderBtnXpath = "//a[text()='Place Order']";
    public String downloadInvoiceBtnXpath = "//a[text()='Download Invoice']";

    public WebElement getTotalAmount(WebDriver driver){
     List<WebElement> totalAmounts = driver.findElements(By.xpath("//p[@class='cart_total_price']"));
     return totalAmounts.get(totalAmounts.size()-1);
    }

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

