package util;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.SystemClock;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavlo_Reshetniak on 19.06.2017.
 */
public class CustomWebDriverWait extends FluentWait<WebDriver> {
    private final WebDriver driver;

    public CustomWebDriverWait(WebDriver driver, long clock, long sleeper) {
        super(driver, new SystemClock(), Sleeper.SYSTEM_SLEEPER);
        withTimeout(clock, TimeUnit.SECONDS);
        pollingEvery(sleeper, TimeUnit.MILLISECONDS);
        ignoreAll(Arrays.asList(NotFoundException.class, StaleElementReferenceException.class, NoSuchElementException.class));
        this.driver = driver;
    }

    @Override
    protected RuntimeException timeoutException(String message, Throwable lastException) {
        TimeoutException ex = new TimeoutException(message, lastException);
        ex.addInfo(WebDriverException.DRIVER_INFO, driver.getClass().getName());

        if (driver instanceof RemoteWebDriver) {
            RemoteWebDriver remote = (RemoteWebDriver) driver;
            if (remote.getSessionId() != null) {
                ex.addInfo(WebDriverException.SESSION_ID, remote.getSessionId().toString());
            }
            if (remote.getCapabilities() != null) {
                ex.addInfo("Capabilities", remote.getCapabilities().toString());
            }
        }
        return ex;
    }
}