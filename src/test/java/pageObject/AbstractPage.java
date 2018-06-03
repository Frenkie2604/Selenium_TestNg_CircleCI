package pageObject;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.springframework.beans.factory.DisposableBean;
import runner.GetUI;
import runner.SetUI;
import runner.VoidUI;
import util.CustomWebDriverWait;
import util.ExtendedExpectedConditions;
import util.WebDriverConfig;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public abstract class AbstractPage implements GetUI<T>, DisposableBean, VoidUI, SetUI<T> {

    final static protected WebDriver driver = WebDriverConfig.getDriver();

    final protected int defaultTimeout = 30;

    public VoidUI refreshThePage = () ->
            driver.navigate().refresh();

    protected void navigateTo(String url) {
        driver.get(url);
    }


    public Collection<WebElement> waitToFindAllElements(By locator, int waitTime) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(waitTime, TimeUnit.SECONDS)
                    .pollingEvery(200, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class);
            wait.until(driverInput -> !driverInput.findElements(locator).isEmpty());
        } catch (TimeoutException e) {
            System.out.println("ERROR: NO Elements were found " + e.getMessage());
        }
        return driver.findElements(locator);
    }

    public WebElement waitTillElementAppears(By locator, int waitTime) {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(waitTime, TimeUnit.SECONDS)
                    .pollingEvery(200, TimeUnit.MILLISECONDS)
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return wait.until(driverInput -> driverInput.findElement(locator));
        } catch (TimeoutException e) {
            System.out.println("ERROR: No Element was found " + e.getMessage());
        }
        return driver.findElement(locator);
    }

    public boolean waitUntil(ExpectedCondition<?> condition, int timeout, int interval) {
        try {
            new CustomWebDriverWait(driver, timeout, interval).until(condition);
            return true;
        } catch (NoSuchElementException | TimeoutException ex) {
            return false;
        }
    }

    public boolean waitUntil(ExpectedCondition<?> condition) {
        return waitUntil(condition, defaultTimeout, 200);
    }

    public Boolean waitForElement(WebElement element) {
        return waitUntil(ExtendedExpectedConditions.presenseAndVisibilityOfElement(element));
    }

    public boolean waitAndClick(WebElement element) {
        if (waitUntil(ExtendedExpectedConditions.presenseAndVisibilityOfElement(element))) {
            return waitUntil(ExtendedExpectedConditions.elementReceivedClick(element));
        }
        return false;
    }

    public boolean waitUntilElementBecomeInvisible(By locator) {
        return new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public VoidUI deleteAllCookies = () -> {
        int attempts = 0;
        System.out.println("INFO: Cleaning all Cookies -> " + driver.manage().getCookies().size() + " <- START");
        while (driver.manage().getCookies().size() > 0) {
            driver.manage().deleteAllCookies();
            attempts++;
            if (attempts > 5) {
                break;
            }
        }
        System.out.println("INFO: Cleaning all Cookies -> " + driver.manage().getCookies().size() + " <- FINISH within " + attempts + " attempts");
    };

    public VoidUI idleWait = () -> {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public Boolean waitIfWebElementIsVisible(By location) {
        return (waitTillElementAppears(location, defaultTimeout).getSize() != null);
    }

    public Boolean checkIfWebElementIsVisible(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void execute() {
    }

    public void fillIn(T data) {
    }

    public void destroy() {
    }

    public T init() {
        return get();
    }

}
