
package listeners;

import base.BaseTest;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import logging.FrameworkLogger;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    private static final Logger log =
            FrameworkLogger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {

        log.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        log.info("Test PASSED: {} | Duration: {} ms",
                result.getName(),
                getExecutionTime(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.error("Test FAILED: {}", result.getName());

        if (result.getThrowable() != null) {
            log.error("Failure reason: ",
                    result.getThrowable());
        }

        captureScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        log.warn("Test SKIPPED: {}", result.getName());

        if (result.getThrowable() != null) {
            log.warn("Skip reason: ",
                    result.getThrowable());
        }
    }

    private void captureScreenshot(ITestResult result) {

        Object testInstance = result.getInstance();

        if (!(testInstance instanceof BaseTest baseTest)) {
            return;
        }

        Page page = baseTest.getPage();

        if (page == null || page.isClosed()) {
            log.warn("Screenshot not captured: Page is unavailable");
            return;
        }

        try {
            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setFullPage(true)
            );

            Allure.addAttachment(
                    "Failure Screenshot - " + result.getName(),
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );

            log.info("Failure screenshot attached to Allure");

        } catch (Exception e) {
            log.error("Unable to capture failure screenshot", e);
        }
    }

    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}