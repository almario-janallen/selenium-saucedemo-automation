package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ScreenshotUtil;
import utils.ExtentManager;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Create Extent test entry
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);

        logger.info("🚀 STARTING TEST: " + result.getName());
        test.get().log(Status.INFO, "Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ PASSED: " + result.getName());
        test.get().log(Status.PASS, "Test Passed");

        captureScreenshot(result, "success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ FAILED: " + result.getName(), result.getThrowable());
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        captureScreenshot(result, "failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ SKIPPED: " + result.getName());
        test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());

        captureScreenshot(result, "skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("=== Test Suite Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=== Test Suite Finished: " + context.getName() + " ===");
        extent.flush(); // Finalize Extent report
    }

    private void captureScreenshot(ITestResult result, String status) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName(), status);
            logger.info("📸 Screenshot saved at: " + screenshotPath);

            // Attach screenshot to Extent report
            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                logger.error("⚠️ Could not attach screenshot to ExtentReport", e);
            }
        }
    }
}
