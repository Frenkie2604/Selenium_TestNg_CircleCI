package util;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class WebDriverConfig {

    public final static String LOGIN_INTERNAL_URL = PropHelper.getPropertyValue("mainURL");


    private final static String CHROME = "chrome";
    private final static String FIREFOX = "firefox";
    private final static String REMOTE = "remote";

    private static String absolute_driver_path;
    private static String Chrome_driverPath;
    private static String Edge_driverPath;
    private static String IE_driverPath;
    private static String FF_driverPath;
    private static String os_Name;
    private static WebDriver driver;
    private static Actions action;

    private WebDriverConfig() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("INFO: New Driver INIT");
            initDriver();
        } else {
            System.out.println("INFO: Working with already initialized Driver");
        }
        return driver;
    }

    public static void quitDriver() {
        System.out.println("INFO: QUITING the DRIVER");
        driver.quit();
    }

    public static Actions getAction() {
        if (action == null)
            initAction();
        return action;
    }

    private static void initAction() {
        action = new Actions(driver);
    }

    private static void getOSName() {
        os_Name = System.getProperty("os.name");
        if (os_Name.toUpperCase().contains("WINDOWS")) {
            os_Name = "/Windows";
        } else {
            os_Name = "/Ubuntu";
        }
    }

    private static void setDriversPath() {
        getOSName();
        Chrome_driverPath = getAbsoluteDriverPath() + os_Name + "/chromedriver/";
        Edge_driverPath = getAbsoluteDriverPath() + os_Name + "/EdgeWebDriver/";
        IE_driverPath = getAbsoluteDriverPath() + os_Name + "/IEDriverServer_Win32/";
        FF_driverPath = getAbsoluteDriverPath() + os_Name + "/geckodriver/";
    }

    private static String getAbsoluteDriverPath() {
        return new File(System.getProperty("user.dir"),
                "/src/test/resources/drivers/OS/").
                getAbsolutePath();
    }

    private static void initDriver() {
        setDriversPath();
        String browserName = PropHelper.getPropertyValue("browser_name");

        System.out.println("browser: " + browserName);

        switch (browserName) {
            case CHROME:
                chromeInit();
                break;
            case FIREFOX:
                firefoxInit();
                break;
            case REMOTE:
                remoteInit();
                break;
            default:
                firefoxInit();
                break;
        }
        driver.manage().window().maximize();
    }

    private static void remoteInit() {

        String hubUrl = PropHelper.getPropertyValue("remote_hub_url");
        System.out.println("selenium hub: " + hubUrl);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        HashMap<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings.popups", 0);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePreferences);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        try {
            driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private static void firefoxInit() {
        System.out.println("os_Name is equal to: " + os_Name);
//        if (os_Name.contains("Win")) {
//            System.setProperty("webdriver.gecko.driver", FF_driverPath + "geckodriver.exe");
//        } else {
//            System.setProperty("webdriver.gecko.driver", FF_driverPath + "geckodriver");
//        }
        System.out.println("the FF driver path is =========> " + FF_driverPath);
        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("-private");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        driver = new FirefoxDriver(capabilities);
    }

    private static void chromeInit() {
        System.out.println("os_Name is equal to: " + os_Name);
        HashMap<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.default_content_settings.popups", 0);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePreferences);
        //options.addArguments("incognito");
        options.addArguments("enable-automation");
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
//        if (os_Name.contains("Win")) {
//            System.setProperty("webdriver.chrome.driver", Chrome_driverPath + "chromedriver.exe");
//        } else {
//            System.setProperty("webdriver.chrome.driver", Chrome_driverPath + "chromedriver");
//        }
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("marionette", true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.out.println("the chrome driver's path is ======> " + Chrome_driverPath);
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.SECONDS); //to be refactored
    }

}
