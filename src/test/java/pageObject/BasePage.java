package pageObject;

import locators.BaseLocators;
import org.openqa.selenium.WebElement;
import runner.GetUI;
import runner.VoidUI;
import util.WebDriverConfig;

public class BasePage extends AbstractPage {

    private GetUI<WebElement> SIGN_IN = () -> waitTillElementAppears(BaseLocators.SIGN_IN, defaultTimeout);

    public GetUI<String> title = () ->
            driver.getTitle();

    public GetUI<String> url = () ->
            driver.getCurrentUrl();

    public VoidUI signIn = () ->
            SIGN_IN.get().click();

    public GetUI<Boolean> pageContainerAppeared = () -> {
        return waitUntil(p ->
                waitTillElementAppears(BaseLocators.PAGE_MAIN_CONTAINER, defaultTimeout).getSize().getHeight() > 0);
    };

    public VoidUI goToBaseURL = () -> navigateTo(WebDriverConfig.LOGIN_INTERNAL_URL);

}