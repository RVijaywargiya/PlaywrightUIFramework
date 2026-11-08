package pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    protected HomePage(Page page) {
        super(page);
    }

    public Locator getUpgradeButtonLocator() {
        Locator upgradeButton = page.getByRole(com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Upgrade"));
        System.out.println("Inside home page");
        return upgradeButton;
    }
}
