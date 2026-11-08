package login;

import base.BaseTest;
import data.DataManager;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

@Listeners(AllureTestNg.class)
public class LoginTest extends BaseTest {

    @Test
    @Description("Verify successful login")
    public void verifySuccessfulLogin() {

        DataManager dataManager = new DataManager();

        String userName = dataManager.getUsername();
        String password = dataManager.getPassword();

        LoginPage loginPage = new LoginPage(page);

        HomePage homePage = loginPage.enterUsername(userName).enterPassword(password).clickLoginButton();

        assertThat(homePage.getUpgradeButtonLocator()).isVisible();
    }
}