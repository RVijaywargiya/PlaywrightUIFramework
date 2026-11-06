package login;

import base.BaseTest;
import data.DataManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        DataManager dataManager = new DataManager();

        String userName = dataManager.getUsername();
        String password = dataManager.getPassword();

        LoginPage loginPage = new LoginPage(page);

        HomePage homePage = loginPage.enterUsername(userName).enterPassword(password).clickLoginButton();

        Assert.assertTrue(homePage.isHomePageDisplayed());

    }
}