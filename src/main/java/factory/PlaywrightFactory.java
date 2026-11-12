package factory;

import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

    public static Playwright createPlaywright() {
        return Playwright.create();
    }
}