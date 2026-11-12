package factory;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class PageFactory {

    public static Page createPage(Browser browser) {

        BrowserContext context = browser.newContext();

        return context.newPage();
    }
}