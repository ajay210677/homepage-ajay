package electronics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class ElectronicTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        // 1.1 Mouse Hover on “Electronics”Tab,mouse hover on 'cell phones' and click
        mouseHoverToElementAndClick(By.linkText("Electronics"), By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        //1.3 Verify the text “Cell phones”
        verifyElements("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"), "Cell phone message not displayed");

    }

    @Test
    public void verifyThatProductAddedSuccessfullyAndPlaceOrderSuccessFully() throws InterruptedException {
        /*
        2.1 Mouse Hover on “Electronics”Tab
        2.2 Mouse Hover on “Cell phones” and click
        2.3 Verify the text “Cell phones”
        these 3 steps can be done by calling previous method
         */
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();
        //2.4 Click on list view tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        Thread.sleep(2000);
        //2.5 Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/h2[1]/a[1]"));
        //2.6 Verify the text “Nokia Lumia 1020”
        verifyElements("Nokia Lumia 1020", By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"), "Nokia Lumia 1020 is displayed incorrectly");
        //2.7 Verify the price “$349.00”
        verifyElements("$349.00", By.xpath("//span[@id='price-value-20']"), "Price is displayed incorrectly");
        //2.8 Change quantity to 2
        Thread.sleep(3000);
        //driver.findElements(By.id("product_enteredQuantity_20")).clear();
        WebElement toClear = driver.findElement(By.id("product_enteredQuantity_20"));
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);//delete '1'
        //Thread.sleep(3000);
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");

        //2.9 Click on “ADD TO CART” tab
        clickOnElement(By.id("add-to-cart-button-20"));
        // Verify the Message "The product has been added to your shopping cart" on Top green Bar

        verifyElements("The product has been added to your shopping cart", By.xpath("//p[@class='content']"), "Message has been displayed incorrectly");

        //After that close the bar clicking on the cross button
        clickOnElement(By.cssSelector("span[title='Close']"));

        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverToElementAndClick(By.xpath("//span[text()='Shopping cart']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[text()='Go to cart']"));
        //Verify the message "Shopping cart
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart displayed incorrectly");
        //Verify the quantity is 2
        // verifyElements("2",By.xpath("//tbody/tr[1]/td[5]/input[@id='itemquantity11243']"),"quantity not added");
        //verified using the get attribute method
        String expectedMessage = "2";
        String actualMessage = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).getAttribute("value");
        Assert.assertEquals("Quantity is not correct", expectedMessage, actualMessage);
        //Verify the Total $698.00
        verifyElements("$698.00", By.xpath("//tbody/tr[1]/td[6]/span[1]"), "Amount is displayed incorrectly");
        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));
        //Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));
        //Verify the Text “Welcome, Please Sign In!”
        verifyElements("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not on the Sign in Page");
        //Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        //Verify the text “Register”
        verifyElements("Register", By.xpath("//h1[contains(text(),'Register')]"), "Registration message is not displayed correctly");
        //Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "john");//firstname
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Smith");//lastname
        sendTextToElement(By.xpath("//input[@id='Email']"), "john720@gmail.com");//email
        sendTextToElement(By.xpath("//input[@id='Password']"), "abc123");//password
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "abc123");//confirm password

        //Click on “REGISTER” Button
        clickOnElement(By.cssSelector("#register-button"));
        Thread.sleep(2000);
        //Verify the message “Your registration completed”
        verifyElements("Your registration completed", By.xpath("//div[@class='result']"), "Registration is incomplete");
        //Click on Continue Tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //Verify the text "Shopping Cart"
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart is not displayed");

        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //Fill the mandatory fields and click on continue
        selectByIndex(By.xpath("//select[@id='BillingNewAddress_CountryId']"), 5);
        sendTextToElement(By.cssSelector("#BillingNewAddress_City"), "London");
        sendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "10 downing street");
        sendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "NR7 5TR");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "0845678910");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        //Click on Radio Button “2nd Day Air($0.00)”
        clickOnElement(By.id("shippingoption_2"));
        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        //Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));

        //Select “Visa” From Select credit card dropdown and fill all details and click on continue
        selectByIndex(By.xpath("//select[@id='CreditCardType']"), 1);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Ajay");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5555 5555 5555 4444");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "12");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "4242");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        /*Verify “Payment Method” is “Credit Card”
        Verify “Shipping Method” is “2nd Day Air”
        Verify Total is “$698.00”*/

        verifyElements("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        verifyElements("2nd Day Air", By.cssSelector("li[class='shipping-method'] span[class='value']"), "Shipping Method is displayed incorrectly");
        verifyElements("$698.00", By.xpath("//span[contains(text(),'$698.00')]"), "Total Amount is displayed incorrectly");
        //Click on confirm
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        //Verify the Text “Thank You”

        //Verify the Text “Thank You”
        verifyElements("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");

        //Verify the message “Your order has been successfully processed!”
        verifyElements("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //Verify the text “Welcome to our store”
        verifyElements("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");

    }
    public void tearDown(){
        closeBrowser();
    }
}


