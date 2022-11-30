package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class TestSuite  extends Utility {

    String baseUrl = "https://demo.nopcommerce.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        //Find the Computer Menu element and click on Computer Menu.
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        // Find the Desktop element and click on Desktop
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h2[1]/a[1]"));
        // Select Sort By Position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");
        String expectedMessage = "Name: Z to A";
        Thread.sleep(2000);
        String actualMessage = getTextFromElement(By.xpath("//option[contains(text(), 'Name: Z to A')]"));
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {
        //Click on computer menu using utility method clickonElement
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));

        //clicking on Desktop link using utility method clickOnElement
        clickOnElement(By.xpath("//div[@class='block block-category-navigation']/descendant::a[2]"));

        //Select sort by position "Name A to Z from dropdown using utility method
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: A to Z");
        Thread.sleep(2000);

        //Click on add to cart
        clickOnElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));


        // Verify the Text "Build your own computer"
        verifyElements("Build your own computer", By.xpath("//h1[contains(text(),'Build your own computer')]"), "User has not navigate successfully");

        //Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7.Select "8GB [+$60.00]" using Select class.
        selectByIndex(By.id("product_attribute_2"), 3);

        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));

        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        //Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        //[+$5.00]"
        Thread.sleep(3000);
        clickOnElement(By.id("product_attribute_5_10"));
        Thread.sleep(3000);
        clickOnElement(By.id("product_attribute_5_10"));
        clickOnElement(By.id("product_attribute_5_12"));
        Thread.sleep(2000);
        //Verify the price "$1,475.00"
        verifyElements("$1,475.00", By.xpath("//span[@id='price-value-1']"), "Inaccurate total");
        Thread.sleep(3000);
        //Click on "ADD TO CART" Button.
        clickOnElement(By.id("add-to-cart-button-1"));

        //Verify the Message "The product has been added to your shopping cart" on Top
        //green bar.
        verifyElements("The product has been added to your shopping cart", By.xpath("//p[text()='The product has been added to your ']"), "Product has not been added to the cart");

        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        // mouseHoverToElementAndClick(By.cssSelector(".cart-label"), By.cssSelector(".button-1.cart-button"));
        mouseHoverToElementAndClick(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        //Verify the message "Shopping cart"
        Thread.sleep(2000);
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "label not displayed");
        Thread.sleep(2000);


        //Change the Qty to "2" and Click on "Update shopping cart"
        //driver.findElements(By.xpath("//input[@class='qty-input' and @value='1']")).clear();
        WebElement toClear = driver.findElement(By.xpath("//input[@class='qty-input' and @value='1']"));
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);//delete '1'
        Thread.sleep(3000);
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");//'write(replace) '2'
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //2.17 Verify the Total"$2,950.00"
        verifyElements("$2,950.00", By.xpath("//span[@class='product-subtotal']"), "The order total is not correct");

        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.19 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        verifyElements("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not on the Sign in Page");

        //2.21 Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        //2.22 Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Chhagan");//firstname
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Rajani");//lastname
        sendTextToElement(By.id("BillingNewAddress_Email"), "xyz123@gmail.com");//email
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");//country
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");//city
        sendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "10 downing street");//Address1
        sendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "NR75TB");//Zip/Postal code
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "0845123456");//Phone number

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //   2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));


        //Select “Master card” From Select credit card dropdown and fill all details and click on continue
        selectByIndex(By.xpath("//select[@id='CreditCardType']"), 1);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "ajay Bhai");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "1234 5678 9101 1121");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "08");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2024");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "3695");
        // 2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        Thread.sleep(2000);
        //Verify “Payment Method” is “Credit Card”
        verifyElements("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        //Verify “Shipping Method” is “2nd Day Air”
        Thread.sleep(2000);
        verifyElements("Next Day Air", By.xpath("//div[@class='shipping-method-info']//ul[1]//li[1]//span[@class='value' and contains(text(),'Next')]"), "Shipping Method is displayed incorrectly");
        //Verify Total is “$698.00”
        verifyElements("$2,950.00", By.xpath("//span[contains(text(),'$2,950.00')]"), "Total Amount is displayed incorrectly");

        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));


        //Verify the Text “Thank You”
        verifyElements("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");

        //Verify the message “Your order has been successfully processed!”
        verifyElements("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //Verify the text “Welcome to our store”
        verifyElements("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
