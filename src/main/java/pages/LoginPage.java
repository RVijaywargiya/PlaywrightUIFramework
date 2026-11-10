package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.options.AriaRole.BUTTON;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    @Step("Enter username")
    public LoginPage enterUsername(String userName) {
        Locator usernameField = getByPlaceholder("Username");
        waitForVisible(usernameField);
        fill(usernameField, userName);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        Locator passwordField = getByPlaceholder("Password");
        waitForVisible(passwordField);
        fill(passwordField, password);
        return this;
    }

    @Step("Click login button")
    public HomePage clickLoginButton() {
        Locator loginBtn = getByRole(BUTTON, "Login");
        click(loginBtn);
        page.waitForTimeout(5000);
        return new HomePage(page);
    }
}
