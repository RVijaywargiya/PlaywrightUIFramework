package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    public static Browser createBrowser(Playwright playwright, String browserName, boolean headless) {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headless);

        return switch (browserName.toLowerCase()) {
            case "chromium" -> playwright.chromium().launch(options);
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browserName);
        };
    }
}