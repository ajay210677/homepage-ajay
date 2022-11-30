package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;



    public class Utility extends BaseTest {
        String actualText;

        // click method
        public void clickOnElement(By by) {
            WebElement element = driver.findElement(by);
            element.click();
        }

        public String getTextFromElement(By by) {
            return driver.findElement(by).getText();

        }

        public void sendingKeyToElement(By by, String value) {
            driver.findElement(by).sendKeys(value);
        }

        //verify  method
        public void verifyText(By by, String expactedText, String actualtext) {
            this.actualText = getTextFromElement(by);
            Assert.assertEquals("verify text", expactedText, actualtext);

        }

        //action class method
        public void actionMethod(By by1, By by2) {
            Actions actions = new Actions(driver);
            WebElement element1 = driver.findElement(by1);
            WebElement element2 = driver.findElement(by2);
            actions.moveToElement(element1).build().perform();
            actions.moveToElement(element2).click().build().perform();

        }

        //get the list size of element method and get the size
        public void getListsSizeOfElement(By by) {
            List<WebElement> lists = driver.findElements(by);
            System.out.println(lists.size());
        }

        public void getTheTextOfAllListAfterFilter(By by) {
            List<WebElement> lists = driver.findElements(by);
            System.out.println(lists.size());
            for (WebElement value : lists) {
                System.out.println(value.getText());
            }
        }

        //alert method
        public String alertPopUpHandling() {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        }

        //switching window method
        public void switchingWindowMethod(By by1, By by2) {
            String parentWindow = driver.getWindowHandle();
            System.out.println(parentWindow);
            clickOnElement(by1);
            Set<String> handles = driver.getWindowHandles();
            System.out.println(handles);
            for (String handle : handles) {
                if (!handle.equals(parentWindow)) {
                    driver.switchTo().window(handle);
                    clickOnElement(by2);
                    driver.close();
                    break;
                }
            }
        }

        public void selectByVisibleTextFromDropDown(By by, String text) {
            WebElement dropDown = driver.findElement(by);
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
        }

        public void selectByValaue(By by, String text) {
            WebElement dropDown = driver.findElement(by);
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
        }

        public void selectByIndex(By by, int num) {
            WebElement dropDown = driver.findElement(by);
            Select select2 = new Select(dropDown);
            select2.selectByIndex(num);
        }

        public void selectByValue(By by, String value) {
            WebElement dropDown = driver.findElement(by);
            Select select3 = new Select(dropDown);
            select3.selectByValue(value);
        }

        public void mouseHoverToElementAndClick(By by) {
            Actions actions = new Actions(driver);
            WebElement software = driver.findElement(by);
            actions.moveToElement(software).click().build().perform();
        }

        public void mouseHoverToElementAndClick(By by1, By by2) {
            Actions hover = new Actions(driver);
            WebElement destination1 = driver.findElement(by1);
            WebElement destination2 = driver.findElement(by2);
            hover.moveToElement(destination1).moveToElement(destination2).click().build().perform();
        }

        public void verifyElements(String expectedMessage, By by, String displayMessage) {
            String actualMessage = getTextFromElement(by);
            Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
        }

        public void sendTextToElement(By by, String text) {
            WebElement inputfield = driver.findElement(by);//
            //Type email to email field
            inputfield.sendKeys(text);

        }

    }