package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SeleniumUtils {
    /**
     * This method clicks on the given element, switches to the newly opened tab and prints it URL
     *
     * @param driver
     * @param element
     */
    public static void switchToNewTab(WebDriver driver, WebElement element) {
        String mainWindow = driver.getWindowHandle();
        element.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(mainWindow)) {
                driver.switchTo().window(windowHandle);
            }
        }
        System.out.println("Currently, the driver is on: " + driver.getCurrentUrl());
    }

    /**
     * This method waits till the web element is clickable
     *
     * @param driver
     * @param element
     */
    public static void click(WebDriver driver, WebElement element) {
        FluentWait wait = new FluentWait(driver).ignoring(ElementClickInterceptedException.class).withTimeout(Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

    }

    /**
     * This method excepts the alert, if alert is not there, it ignores it
     *
     * @param driver
     */
    public static void acceptAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert doesn't exist");
            e.printStackTrace();
        }
    }

    public static void dismissAlert(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.alertIsPresent());
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert doesn't exist");
            e.printStackTrace();
        }
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void clickWithRetries(WebDriver driver, By locator, int retries) {
        int numOfTrials = 0;
        while (numOfTrials < retries) {
            try {
                WebElement element = driver.findElement(locator);
                element.click();
            } catch (StaleElementReferenceException e) {
                numOfTrials++;
                waitForSeconds(1);
            } catch (NoSuchElementException e) {
                System.out.println("Wrong locator");
                e.printStackTrace();
                return;
            }

        }

    }
    public static void waitForSeconds( int numbersOfSeconds){
        try {
            Thread.sleep(numbersOfSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void verifyTextInElement(WebDriver driver, int numOfSec, WebElement element, String expectedText){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(numOfSec));
        wait.until(ExpectedConditions.textToBePresentInElement(element,expectedText));

        Assert.assertEquals(expectedText, element.getText());
    }

    public static void clearAndSendKeys(WebDriver driver, WebElement element, String textToSend){
        Actions actions = new Actions(driver);
        actions.keyDown(element,Keys.COMMAND).sendKeys("a");
        actions.keyUp(Keys.COMMAND);
        actions.keyDown(element,Keys.BACK_SPACE);
        actions.keyUp(element,Keys.BACK_SPACE);
        actions.build().perform();
        element.sendKeys(textToSend);

    }
}