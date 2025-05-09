package pages.signupLogin;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;

public class SignupLogin {
    public String getLoginSignUpPageElementXpath(String elementType,String elementName){
        return "//"+elementType+"[@data-qa='"+elementName+ "']";
    }
    public void login(String email, String password, WebDriver driver) {
        //email yaz
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","login-email"))).sendKeys(email);
        //password yaz
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","login-password"))).sendKeys(password);
        // login duymesine bas
       driver.findElement(By.xpath(getLoginSignUpPageElementXpath("button","login-button"))).click();
    }

    public HashMap<String,String> registerNewUser(WebDriver driver){
        Faker faker = new Faker();
        String username = faker.name().username();
        String emailAddress=faker.internet().emailAddress();
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","signup-name")))
                .sendKeys(username);
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","signup-email")))
                .sendKeys(emailAddress);
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("button","signup-button")))
                .click();
        HashMap<String,String> newUserData = new HashMap<>();
        newUserData.put("username",username);
        newUserData.put("email",emailAddress);
        return newUserData;
    }
}

