package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;

    public void openBrowser(String baseUrl){

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

        driver = new ChromeDriver(); //Created Object

        // 1) Launch Url
        driver.get(baseUrl);

        //2)Maximise Window
        driver.manage().window().maximize();

        //3) Giving Implict Time to Driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


    }

    public void closeBrowser(){

       // driver.quit();
    }


}
