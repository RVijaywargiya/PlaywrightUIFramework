package utils;

import com.microsoft.playwright.Page;

import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtils {

    public void takeScreenshot(Page page, String fileName) {

        try {
            Path directory = Path.of("target/test-artifacts/screenshots");
            Files.createDirectories(directory);

            Path screenshotPath = directory.resolve(fileName);

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(screenshotPath)
                            .setFullPage(true)
            );

            System.out.println("Screenshot saved: " + screenshotPath.toAbsolutePath());

        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}
