package login;

import base.BaseTest;
import io.qameta.allure.testng.AllureTestNg;
import models.LoginTestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Listeners(AllureTestNg.class)
public class LoginTest extends BaseTest {

//    @DataProvider(name = "loginData")
//    public Object[][] loginDataProvider() {
//
//        List<LoginTestData> data =
//                TestDataReader.readList(
//                        "loginData.json",
//                        LoginTestData.class);
//
//        return data.stream()
//                .map(testData -> new Object[]{testData})
//                .toArray(Object[][]::new);
//    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {

        return new Object[][]{
                {"standard_user", "secret_password"},
                {"invalid_user", "wrong_password"}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifySuccessfulLogin(String username, String password) {

//        String userName = data.getUsername();
//        String password = data.getPassword();

        LoginPage loginPage = new LoginPage(page);

        HomePage homePage = loginPage
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        assertThat(homePage.getUpgradeButtonLocator()).isVisible();
    }
}