package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeTest
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));
        page = context.newPage();
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        assertThat(page).hasTitle(Pattern.compile("OrangeHRM"));
    }

    @AfterTest
    public void tearDown() {
        // Cleanup
        context.close();
        System.out.println("Context closed");
        browser.close();
        System.out.println("Browser closed");
        playwright.close();
        System.out.println("Playwright closed");
    }

}
