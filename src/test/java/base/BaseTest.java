package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import config.ApplicationConfig;
import config.ConfigManager;
import factory.BrowserFactory;
import factory.PlaywrightFactory;
import logging.FrameworkLogger;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected ApplicationConfig config;

    protected static final Logger log = FrameworkLogger.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        config = ConfigManager.getConfig();
        playwright = PlaywrightFactory.createPlaywright();

        log.info("Opening browser...");
        browser = BrowserFactory.createBrowser(playwright, config.browser(), config.headless());
        context = browser.newContext();
        page = context.newPage();

        log.info("Navigating to the URL");
        page.navigate(config.baseUrl());
    }

    @AfterMethod
    public void tearDown() {
        log.info("Closing browser");
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}