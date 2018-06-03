package pageObject;

import locators.BaseLocators;
import locators.ModalLocators;
import org.openqa.selenium.WebElement;
import runner.GetUI;
import runner.VoidUI;

public class ModalWindowPage extends AbstractPage{

    private GetUI<WebElement> MODAL_CONTAINER = () -> waitTillElementAppears(ModalLocators.MODAL_CONTAINER, defaultTimeout);
    private GetUI<WebElement> MODAL_CLOSE = () -> waitTillElementAppears(ModalLocators.MODAL_CLOSE, defaultTimeout);

    public VoidUI closeModalIfOpen = () -> {
        if (checkIfWebElementIsVisible(ModalLocators.MODAL_CLOSE))
            MODAL_CLOSE.get().click();
    };

}
