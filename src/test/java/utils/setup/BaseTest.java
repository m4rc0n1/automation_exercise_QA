package utils.setup;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.cart.CartPage;
import pages.home.HomePage;
import pages.signupLogin.SignupLogin;
import utils.ChromeDriverConfig;
import utils.ConfigReader;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader configReader = new ConfigReader();
    protected HomePage homePage = new HomePage();
    protected SignupLogin signupLoginPage = new SignupLogin();
    protected CartPage cartPage = new CartPage();
    protected Faker faker= new Faker();
    protected String downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
    protected String emailData = configReader.getProperty("email");
    protected String passwordData = configReader.getProperty("password");

//    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(){
//        System.out.println("Setting up new browser environment");
//        switch(browser.toLowerCase()){
//            case "chrome":
//                driver = ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
//                break;
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//            case "edge":
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//                break;
//            default:
//                throw new IllegalArgumentException("Browser not supported " + browser);
//        }

        if(configReader.getProperty("browser").equals("chrome")){
        driver= ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
    }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(configReader.getProperty("url"));
        try{
            driver.findElement(By.xpath("//button[@aria-label='Consent']")).click();
        }catch(NoSuchElementException err){
            System.out.println("Can not find Consent button");
        }
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
