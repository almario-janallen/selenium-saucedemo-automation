package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName, String status) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dirPath = "test-output/screenshots/" + status; // success or failure
        new File(dirPath).mkdirs();

        String screenshotFileName = timestamp + "_" + testName + ".png";

        String screenshotPath = dirPath + "/" + screenshotFileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return path relative to ExtentReport.html
        return "screenshots/" + status + "/" + screenshotFileName;
    }
}
