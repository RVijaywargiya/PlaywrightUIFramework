package login;

import base.BaseTest;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Listeners(AllureTestNg.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {

        return new Object[][]{
                {"ADMIN", "admin123"}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifySuccessfulLogin(String username, String password) {

        LoginPage loginPage = new LoginPage(page);

        HomePage homePage = loginPage
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        assertThat(homePage.getUpgradeButtonLocator()).isVisible();

        homePage.logout();
    }
}