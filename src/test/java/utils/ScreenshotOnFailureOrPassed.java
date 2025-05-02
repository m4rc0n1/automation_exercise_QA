package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

public class ScreenshotOnFailureOrPassed implements TestWatcher {
    private static WebDriver driver;
    public static void setDriver(WebDriver driver){
        ScreenshotOnFailureOrPassed.driver = driver;
    }

    @Override
    public void testFailed(ExtensionContext context,Throwable cause){
        Screenshot.takeScreenshot(driver,"Test failed");
        TestWatcher.super.testFailed(context,cause);
    }

    @Override
    public void testSuccessful(ExtensionContext context){
        Screenshot.takeScreenshot(driver,"Test failed");
        TestWatcher.super.testSuccessful(context);
    }
}
