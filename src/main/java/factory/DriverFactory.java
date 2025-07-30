package factory;

import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver() {
        // Prefer system properties, fallback to config file
        String browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.get("browser");
            if (browser == null || browser.isEmpty()) {
                browser = "chrome";
            }
        }
        String headlessProp = System.getProperty("headless");
        boolean headless;
        if (headlessProp != null) {
            headless = Boolean.parseBoolean(headlessProp);
        } else {
            String headlessConfig = ConfigReader.get("headless");
            headless = headlessConfig != null && headlessConfig.equalsIgnoreCase("true");
        }
        setDriver(browser, headless);
    }

    public static void setDriver(String browser, boolean headless) {
        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case "safari":
                // Safari doesn't support headless mode as of now
                webDriver = new SafariDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--disable-gpu");
                }
                webDriver = new ChromeDriver(chromeOptions);
        }
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}