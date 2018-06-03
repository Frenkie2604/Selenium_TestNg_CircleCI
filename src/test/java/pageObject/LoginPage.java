package pageObject;

import locators.LoginLocators;
import org.openqa.selenium.WebElement;
import runner.GetUI;
import runner.VoidUI;

public class LoginPage extends AbstractPage {

    private GetUI<WebElement> SIGN_IN_WITH_FACEBOOK = () -> waitTillElementAppears(LoginLocators.SIGN_WITH_FACEBOOK, defaultTimeout);
    private GetUI<WebElement> SIGN_IN_WITH_GOOGLE = () -> waitTillElementAppears(LoginLocators.SIGN_WITH_GOOGLE, defaultTimeout);
    private GetUI<WebElement> SIGN_IN_WITH_EMAIL = () -> waitTillElementAppears(LoginLocators.SIGN_WITH_EMAIL, defaultTimeout);

    public VoidUI signInWithFacebook = () -> SIGN_IN_WITH_FACEBOOK.get().click();
    public VoidUI signInWithGoogle = () -> SIGN_IN_WITH_GOOGLE.get().click();
    public VoidUI signInWithEmail = () -> SIGN_IN_WITH_EMAIL.get().click();

}