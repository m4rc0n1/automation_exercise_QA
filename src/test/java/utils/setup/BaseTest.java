package utils.setup;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
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

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup(){
        System.out.println("Automation Exercises suite has been started");
    }

    @BeforeGroups(groups = {"regression"})
    public void regressionGroupsBefore(){
        System.out.println("Regression Test has been started");
    }

    @BeforeGroups(groups = {"smoke"})
    public void smokeGroupsBefore(){
        System.out.println("Smoke Test has been started");
    }
    @BeforeClass(alwaysRun = true)
    public void classSetup(){
        System.out.println("Running classSetup before");
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(String browser){
        switch (browser.toLowerCase()){
            case "chrome":
                driver= ChromeDriverConfig.getConfiguratedChromeDriver(downloadPath);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default: throw new IllegalArgumentException("Not supported browser: " + browser);
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
