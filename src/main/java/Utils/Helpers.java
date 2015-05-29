package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

import java.net.URL;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class Helpers {
    public static WebDriver driver;
    private static WebDriverWait driverWait;

    /**
     * Initialize the webdriver. Must be called before using any helper methods. *
     */
    public static void init(@Optional("vnsquall") String username,
                            @Optional("23c1faf1-4616-4820-bcb8-d9f7b91562f1") String key,
                            DesiredCapabilities capabilities) throws Exception {

        driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);

        //Load target Website
        driver.get("http://the-internet.herokuapp.com/");

        int timeoutInSeconds = 60;
        driverWait = new WebDriverWait(driver, timeoutInSeconds);
    }

    /**
     * Return an element by locator *
     */
    public static WebElement find_element(By locator) {
        return driver.findElement(locator);
    }

}
