package Nopcommerce;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;


    public class TopMenuTest extends Utility {

        String baseUrl = "https://demo.nopcommerce.com/";

        @Before
        public void setUp() {
            openBrowser(baseUrl);
        }

        public void selectMenu(String menu) throws InterruptedException {
            List<WebElement> topMenu = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']"));
            for (WebElement topMenu1 : topMenu) {
                if (topMenu1.getText().equalsIgnoreCase(menu)) {
                    Thread.sleep(2000);
                    topMenu1.click();
                    break;
                }
            }

        }

        @Test

        public void verifyElectronicsPageNavigation() throws InterruptedException {
            selectMenu("Electronics");
            //Verification using assertion
            verifyElements("Electronics", By.partialLinkText("Electronics"), "Page not found");
        }

        private void verifyElements(String electronics, By electronics1, String page_not_found) {
        }

        @Test
        public void verifyComputerPageNavigation() throws InterruptedException {
            selectMenu("Computers");
            //Verification using assertion
            verifyElements("Computers", By.partialLinkText("Computers"), "Page not found");
        }

        @Test
        public void verifyApparelPageNavigation() throws InterruptedException {
            selectMenu("Apparel");
            //Verification using assertion
            verifyElements("Apparel", By.partialLinkText("Apparel"), "Page not found");
        }

        public void tearDown(){
            closeBrowser();
        }

     

}
