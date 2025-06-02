package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverConfig {
    public static WebDriver getConfiguratedChromeDriver(String downloadFilePath) {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilePath);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver(options);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("safebrowsing-disable-extension-blacklist");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}