package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverConfig {
    public static WebDriver getConfiguratedChromeDriver(String downloadPath){
        Map<String, Object> prefs = new HashMap<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        prefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}
