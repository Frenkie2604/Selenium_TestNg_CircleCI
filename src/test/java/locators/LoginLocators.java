package locators;

import org.openqa.selenium.By;

public class LoginLocators {

    public static final By SIGN_WITH_FACEBOOK = By.xpath(".//button[contains(@class, 'facebook')]");
    public static final By SIGN_WITH_GOOGLE = By.xpath(".//button[contains(@class, 'google')]");
    public static final By SIGN_WITH_EMAIL = By.xpath(".//a[contains(@class, 'primary')]");

}
