package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Screenshoter;
import utils.WebDriverCreator;

public class DefaultPage {

    private static final int DEFAULT_TIMEOUT = 30;
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    protected Actions action;


    public DefaultPage() {
        driver = WebDriverCreator.getWebDriver();
        jsExecutor = ((JavascriptExecutor) driver);
        action = new Actions(driver);
    }

    protected void click(By locator) {
        WebElement element = driver.findElement(locator);
        highlightElements(locator);
        Screenshoter.makeScreen();
        unHighlightElements(locator);
        action.moveToElement(element).click().build().perform();
    }

    protected void type(By locator, String text){
        WebElement element = driver.findElement(locator);
        highlightElements(locator);
        element.clear();
        element.sendKeys(text);
        Screenshoter.makeScreen();
        unHighlightElements(locator);
    }

    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void waitForElementPresent(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void highlightElements(By locator) {
        jsExecutor.executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
    }

    protected void unHighlightElements(By locator) {
        jsExecutor.executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }

    private ExpectedCondition<Boolean> isAjaxFinished() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return jQuery.active == 0");
            }
        };
    }

    protected void waitForAjaxProccessed() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(isAjaxFinished());
    }

    protected static void switchToNewWindow(WebDriver driver) {
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }
        driver.manage().window().maximize();
    }

    protected static void swithToMainWindow(WebDriver driver){
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }
}
