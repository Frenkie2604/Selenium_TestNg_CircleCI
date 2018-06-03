package locators;

import org.openqa.selenium.By;

public class ModalLocators {

    public static final By MODAL_CONTAINER = By.xpath(".//div[contains(@class, 'dialog--active')]");
    public static final By MODAL_CLOSE = By.xpath(".//div[contains(@class, 'dialog--active')]//i[text()='close']/../parent::button");

}
