package factory;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class APIRequestContextFactory {

    public APIRequestContext createApiRequestContext(Playwright playwright) {
        return playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://api.github.com"));
    }
}
