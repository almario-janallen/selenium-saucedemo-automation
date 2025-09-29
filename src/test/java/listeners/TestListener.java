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
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);

        logger.info("🚀 STARTING TEST: " + result.getName());
        if (test.get() != null) {
            test.get().log(Status.INFO, "Starting test: " + result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✅ PASSED: " + result.getName());
        if (test.get() != null) {
            test.get().log(Status.PASS, "Test Passed");
            attachScreenshotIfAvailable(result, "success");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ FAILED: " + result.getName(), result.getThrowable());
        if (test.get() != null) {
            test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
            attachScreenshotIfAvailable(result, "failure");
        } else {
            logger.warn("⚠️ ExtentTest is null. Skipping logging for failed test: " + result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ SKIPPED: " + result.getName());
        if (test.get() != null) {
            test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
            attachScreenshotIfAvailable(result, "skipped");
        }
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("=== Test Suite Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("=== Test Suite Finished: " + context.getName() + " ===");
        extent.flush();
    }

    private void attachScreenshotIfAvailable(ITestResult result, String status) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).getDriver();
            if (driver != null) {
                try {
                    String path = ScreenshotUtil.captureScreenshot(driver, result.getName(), status);
                    if (test.get() != null) {
                        test.get().addScreenCaptureFromPath(path);
                        logger.info("📸 Screenshot attached to report: " + path);
                    }
                } catch (org.openqa.selenium.NoSuchSessionException e) {
                    logger.warn("⚠️ WebDriver session is gone. Cannot take screenshot: " + e.getMessage());
                } catch (Exception e) {
                    logger.error("⚠️ Failed to attach screenshot to report", e);
                }
            } else {
                logger.warn("⚠️ WebDriver is null. Screenshot not taken for test: " + result.getName());
            }
        }
    }
}
