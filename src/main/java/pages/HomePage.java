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
        Locator locator = page.locator("img[alt='profile picture'][class='oxd-userdropdown-img']");
        click(locator);
        page.waitForTimeout(5000);
        click(getByRole(AriaRole.MENUITEM, "Logout"));
    }


}
