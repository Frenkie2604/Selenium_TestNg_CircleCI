package locators;

import org.openqa.selenium.By;
import util.PropHelper;

public abstract class BaseLocators {

    public final static By PAGE_MAIN_CONTAINER = By.xpath(".//div[@class='application--wrap']");
    public final static By SIGN_IN = By.xpath(".//a[@href='/login-select']");

}
