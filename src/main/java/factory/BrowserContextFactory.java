package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Browser.NewContextOptions;

public class BrowserContextFactory {

    public static BrowserContext createContext(Browser browser) {

        return browser.newContext(
                new NewContextOptions()
                        .setViewportSize(1920, 1080)
                        .setIgnoreHTTPSErrors(true)
        );
    }
}