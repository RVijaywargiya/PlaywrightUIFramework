
package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;
import java.util.List;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    // =========================================================
    // NAVIGATION
    // =========================================================

    public void navigate(String url) {
        page.navigate(url);
    }

    public void goBack() {
        page.goBack();
    }

    public void goForward() {
        page.goForward();
    }

    public void refresh() {
        page.reload();
    }

    public String getCurrentUrl() {
        return page.url();
    }

    public String getPageTitle() {
        return page.title();
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    protected Locator locator(String selector) {
        return page.locator(selector);
    }

    protected Locator getByRole(
            AriaRole role, String name) {
        return page.getByRole(role,
                new Page.GetByRoleOptions().setName(name));
    }

    protected Locator getByText(String text) {
        return page.getByText(text);
    }

    protected Locator getByLabel(String label) {
        return page.getByLabel(label);
    }

    protected Locator getByPlaceholder(String placeholder) {
        return page.getByPlaceholder(placeholder);
    }

    protected Locator getByTestId(String testId) {
        return page.getByTestId(testId);
    }

    protected Locator getByAltText(String altText) {
        return page.getByAltText(altText);
    }

    protected Locator getByTitle(String title) {
        return page.getByTitle(title);
    }

    // =========================================================
    // CLICK ACTIONS
    // =========================================================

    protected void click(String selector) {
        locator(selector).click();
    }

    protected void click(Locator locator) {
        locator.click();
    }

    protected void doubleClick(String selector) {
        locator(selector).dblclick();
    }

    protected void rightClick(String selector) {
        locator(selector).click(
                new Locator.ClickOptions()
                        .setButton(MouseButton.RIGHT));
    }

    protected void clickWithForce(String selector) {
        locator(selector).click(
                new Locator.ClickOptions().setForce(true));
    }

    // =========================================================
    // TEXT INPUT
    // =========================================================

    protected void fill(String selector, String value) {
        locator(selector).fill(value);
    }

    protected void fill(Locator locator, String value) {
        locator.fill(value);
    }

    protected void type(String selector, String value) {
        locator(selector).pressSequentially(value);
    }

    protected void clear(String selector) {
        locator(selector).clear();
    }

    protected void press(String selector, String key) {
        locator(selector).press(key);
    }

    protected void pressSequentially(
            String selector, String text) {
        locator(selector).pressSequentially(text);
    }

    // =========================================================
    // TEXT AND ATTRIBUTE RETRIEVAL
    // =========================================================

    protected String getText(String selector) {
        return locator(selector).innerText();
    }

    protected String getTextContent(String selector) {
        return locator(selector).textContent();
    }

    protected String getInputValue(String selector) {
        return locator(selector).inputValue();
    }

    protected String getAttribute(
            String selector, String attribute) {
        return locator(selector).getAttribute(attribute);
    }

    protected String getElementValue(String selector) {
        return locator(selector).getAttribute("value");
    }

    // =========================================================
    // VISIBILITY AND STATE
    // =========================================================

    protected boolean isVisible(String selector) {
        return locator(selector).isVisible();
    }

    protected boolean isHidden(String selector) {
        return locator(selector).isHidden();
    }

    protected boolean isEnabled(String selector) {
        return locator(selector).isEnabled();
    }

    protected boolean isDisabled(String selector) {
        return locator(selector).isDisabled();
    }

    protected boolean isEditable(String selector) {
        return locator(selector).isEditable();
    }

    protected boolean isChecked(String selector) {
        return locator(selector).isChecked();
    }

    protected boolean isElementPresent(String selector) {
        return locator(selector).count() > 0;
    }

    // =========================================================
    // WAIT OPERATIONS
    // =========================================================

    protected void waitForVisible(String selector) {
        locator(selector).waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));
    }

    protected void waitForHidden(String selector) {
        locator(selector).waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.HIDDEN));
    }

    protected void waitForAttached(String selector) {
        locator(selector).waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.ATTACHED));
    }

    protected void waitForDetached(String selector) {
        locator(selector).waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.DETACHED));
    }

    protected void waitForPageLoad() {
        page.waitForLoadState();
    }

    protected void waitForDomContentLoaded() {
        page.waitForLoadState(
                LoadState.DOMCONTENTLOADED);
    }

    protected void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    protected void waitForUrl(String url) {
        page.waitForURL(url);
    }

    // =========================================================
    // CHECKBOX AND RADIO
    // =========================================================

    protected void check(String selector) {
        locator(selector).check();
    }

    protected void uncheck(String selector) {
        locator(selector).uncheck();
    }

    protected void selectRadio(String selector) {
        locator(selector).check();
    }

    // =========================================================
    // DROPDOWNS
    // =========================================================

    protected void selectOptionByValue(
            String selector, String value) {
        locator(selector).selectOption(value);
    }

    protected void selectOptionByLabel(
            String selector, String label) {
        locator(selector).selectOption(
                new SelectOption().setLabel(label));
    }

    protected void selectOptionByIndex(
            String selector, int index) {
        locator(selector).selectOption(
                new SelectOption().setIndex(index));
    }

    protected List<String> getDropdownOptions(
            String selector) {
        return locator(selector).locator("option")
                .allTextContents();
    }

    // =========================================================
    // MOUSE ACTIONS
    // =========================================================

    protected void hover(String selector) {
        locator(selector).hover();
    }

    protected void scrollIntoView(String selector) {
        locator(selector).scrollIntoViewIfNeeded();
    }

    protected void dragAndDrop(
            String source, String target) {
        page.locator(source).dragTo(page.locator(target));
    }

    // =========================================================
    // KEYBOARD ACTIONS
    // =========================================================

    protected void pressKey(String key) {
        page.keyboard().press(key);
    }

    protected void typeText(String text) {
        page.keyboard().type(text);
    }

    protected void keyboardInsertText(String text) {
        page.keyboard().insertText(text);
    }

    // =========================================================
    // FRAMES
    // =========================================================

    protected FrameLocator frameLocator(String selector) {
        return page.frameLocator(selector);
    }

    // =========================================================
    // ALERTS
    // =========================================================

    protected void acceptAlert() {
        page.onDialog(Dialog::accept);
    }

    protected void dismissAlert() {
        page.onDialog(Dialog::dismiss);
    }

    // =========================================================
    // SCREENSHOTS
    // =========================================================

    public void takeScreenshot(String filePath) {
        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(filePath))
                        .setFullPage(true));
    }

    protected void takeElementScreenshot(
            String selector, String filePath) {
        locator(selector).screenshot(
                new Locator.ScreenshotOptions()
                        .setPath(Paths.get(filePath)));
    }

    // =========================================================
    // ELEMENT COUNTS
    // =========================================================

    protected int getElementCount(String selector) {
        return locator(selector).count();
    }

    // =========================================================
    // PAGE ACCESS
    // =========================================================

    public Page getPage() {
        return page;
    }
}