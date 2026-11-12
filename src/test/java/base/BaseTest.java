package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import config.ApplicationConfig;
import config.ConfigManager;
import factory.BrowserFactory;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected ApplicationConfig config;

    @BeforeMethod
    public void setUp() {
        config = ConfigManager.getConfig();
        playwright = PlaywrightFactory.createPlaywright();
        browser = BrowserFactory.createBrowser(playwright, config.browser(), config.headless());
        context = browser.newContext();
        page = context.newPage();
        page.navigate(config.baseUrl());
    }

    @AfterMethod
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}