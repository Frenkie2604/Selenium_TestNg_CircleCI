package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by Pavlo_Reshetniak on 19.06.2017.
 */
public class ExtendedExpectedConditions {
    private ExtendedExpectedConditions() {

    }

    public static ExpectedCondition<Boolean> textToBePresentInElement(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver arg0) {
                try {
                    String elementText = element.getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException | NoSuchElementException ex) {
                    return null;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> presenseAndVisibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver arg0) {
                try {
                    return element.isDisplayed();
                } catch (StaleElementReferenceException | NoSuchElementException ex) {
                    return false;
                }
            }
        };
    }

    public static ExpectedCondition<Boolean> elementReceivedClick(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver arg0) {
                try {
                    element.click();
                    return true;
                } catch (WebDriverException ex) {
                    return false;
                }
            }
        };
    }
}