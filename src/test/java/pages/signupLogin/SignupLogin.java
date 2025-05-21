package pages.signupLogin;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class SignupLogin {
    public String accountCreatedTextXpath= "//b[text()='Account Created!']";
    public WebElement getTitleEl(WebDriver driver, int titleNum) {
      return driver.findElement(By.xpath("//input[@id='id_gender" +titleNum+"']"));
    }

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
        String password = faker.internet().password();
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","signup-name")))
                .sendKeys(username);
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","signup-email")))
                .sendKeys(emailAddress);
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("button","signup-button")))
                .click();
        HashMap<String,String> newUserData = new HashMap<>();
        newUserData.put("username",username);
        newUserData.put("email",emailAddress);
        newUserData.put("password",password);
        // title sec
        getTitleEl(driver,1).click();
        // password elave et
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input","password")))
                .sendKeys(password);
        WebElement dayDropDownEl = driver.findElement(By.xpath(getLoginSignUpPageElementXpath("select","days")));
        Select daySelect = new Select(dayDropDownEl);
        daySelect.selectByIndex(3);

        WebElement monthDropDownEl = driver.findElement(By.xpath(getLoginSignUpPageElementXpath("select", "months")));
        Select monthSelect =  new Select(monthDropDownEl);
        monthSelect.selectByIndex(4);

        WebElement yearDropDownEl = driver.findElement(By.xpath(getLoginSignUpPageElementXpath("select", "years")));
        Select yearSelect =  new Select(yearDropDownEl);
        yearSelect.selectByIndex(6);

        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input", "first_name")))
                .sendKeys(faker.name().firstName());
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input", "last_name")))
                .sendKeys(faker.name().lastName());
        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("input", "address")))
                .sendKeys(faker.address().fullAddress());
        WebElement countryDropDownEl = driver.findElement(By.xpath(getLoginSignUpPageElementXpath("select","country")));
        Select counrtySelect = new Select(countryDropDownEl);
        counrtySelect.selectByIndex(2);
        Actions actions = new Actions(driver);
        actions.moveToElement(countryDropDownEl).perform();
        actions.sendKeys(Keys.TAB)
                .sendKeys(faker.address().state())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().city())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(getLoginSignUpPageElementXpath("button","create-account"))));
//        driver.findElement(By.xpath(getLoginSignUpPageElementXpath("button","create-account"))).click();
        return newUserData;
    }
}

