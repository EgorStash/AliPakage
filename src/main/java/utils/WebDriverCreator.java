package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class WebDriverCreator {
    private static WebDriver driverInstance;

    private WebDriverCreator() {
    }

    public static WebDriver getWebDriver() {
        if (driverInstance != null) {
            return driverInstance;
        }
        return driverInstance = init();
    }

    private static WebDriver init() {
        System.setProperty("webdriver.chrome.driver", String.valueOf(Paths.get("drivers", "chromedriver.exe")));
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill() {
        if (driverInstance != null) {
            try {
                driverInstance.quit();
            } catch (Exception e) {
                System.out.println("Cannot kill browser");
            } finally {
                driverInstance = null;
            }
        }
    }
}
