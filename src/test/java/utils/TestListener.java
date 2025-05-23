package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    private static WebDriver driver;
    public static void setDriver(WebDriver driver){
       TestListener.driver = driver;
    }
    public void takeScreenShot (String testName){
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenShot, new File("screenshots/"+testName+ ".png"));
            System.out.println("Successfully saved screenshot for test: "+testName);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Failed to save screenshot for test: "+testName);
        }
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: "+ result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: "+ result.getName());
        if(driver!=null)
        takeScreenShot(result.getName());
        else System.out.println("Webdriver is null");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
