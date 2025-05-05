package pages.signupLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLogin {
    private String loginEmailXpath = "//input[@data-qa='login-email']";
    private String loginPasswordXpath = "//input[@data-qa='login-password']";
    private String loginBtnXpath = "//button[@data-qa='login-button']";
    public void login(String email, String password, WebDriver driver) {
        //email yaz
        driver.findElement(By.xpath(loginEmailXpath)).sendKeys(email);
        //password yaz
        driver.findElement(By.xpath(loginPasswordXpath)).sendKeys(password);
        // login duymesine bas
       driver.findElement(By.xpath(loginBtnXpath)).click();
    }
}

