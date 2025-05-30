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

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }
}