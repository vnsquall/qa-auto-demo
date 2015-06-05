package Sauce_lab;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;

import static org.testng.Assert.assertEquals;

/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>.
 *
 * @author Ross Rowe
 * To run this test: change filename to To run this test: change filename to WebDriverTest
 *
 */
public class WebDriverDemo {

    private org.openqa.selenium.WebDriver driver;

    /**
     * Creates a new {@link RemoteWebDriver} instance to be used to run WebDriver tests using Sauce.
     *
     * @param username the Sauce username
     * @param key the Sauce access key
     * @param os the operating system to be used
     * @param browser the name of the browser to be used
     * @param browserVersion the version of the browser to be used
     * @param method the test method being executed
     * @throws Exception thrown if any errors occur in the creation of the WebDriver instance
     */
    @Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("vnsquall") String username,
                      @Optional("23c1faf1-4616-4820-bcb8-d9f7b91562f1") String key,
                      @Optional("Windows 2003") String os,
                      @Optional("firefox") String browser,
                      @Optional("17") String browserVersion,
                      Method method) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", os);
        capabilities.setCapability("name", method.getName());
        this.driver = new RemoteWebDriver(
                new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
    }

    @Test
    public void webDriver_basic_authen() throws Exception {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
//        driver.findElement(By.cssSelector("p"));
//        assertEquals(driver.getTitle(), "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
        assertEquals(driver.findElement(By.cssSelector("p")).getText(),"Congratulations! You must have the proper credentials.", "Message Not Found");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
