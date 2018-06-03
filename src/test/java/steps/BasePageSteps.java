package steps;

import pageObject.BasePage;
import util.WebDriverConfig;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.*;

public class BasePageSteps {

    private final static BasePage BASE_PAGE = new BasePage();

    public static void openBasePage() {
        BASE_PAGE.goToBaseURL.execute();
        assertThat(BASE_PAGE.url.get().trim(),
                containsString(WebDriverConfig.LOGIN_INTERNAL_URL.trim()));
    }

    public static void clickSignIn() {
        BASE_PAGE.signIn.execute();
        assertTrue(BASE_PAGE.pageContainerAppeared.get());
    }

}
