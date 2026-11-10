package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends BasePage {
    protected HomePage(Page page) {
        super(page);
    }

    public Locator getUpgradeButtonLocator() {
        Locator upgradeButton = getByRole(AriaRole.BUTTON, "Upgrade");
        System.out.println("Inside home page");
        return upgradeButton;
    }

    public void logout() {
        click(getByRole(AriaRole.PARAGRAPH, "Leticia Amanda"));
        click(getByRole(AriaRole.LINK, "Logout"));
//        new LoginPage(page);
    }


}
