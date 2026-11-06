package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    public LoginPage enterUsername(String userName) {
        Locator usernameField = page.getByPlaceholder("Username");
        usernameField.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        usernameField.fill(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        Locator passwordField = page.getByPlaceholder("Password");
        passwordField.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        passwordField.fill(password);
        return this;
    }

    public HomePage clickLoginButton() {
        Locator loginBtn = page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("login"));
        loginBtn.click();
        page.waitForTimeout(5000);
        return new HomePage(page);
    }
}
