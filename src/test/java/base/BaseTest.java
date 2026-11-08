package base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    protected void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterClass
    protected void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    protected void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        page = context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        assertThat(page).hasTitle(Pattern.compile("OrangeHRM"));
    }

    @AfterMethod
    protected void closeContext() {
        // Cleanup
        context.close();
        System.out.println("Context closed");
    }

}
