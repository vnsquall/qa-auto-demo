package sauce_lab;

import com.saucelabs.common.SauceOnDemandAuthentication;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * @author Ross Rowe
 */
public class WebDriverDemoShootoutTest {

    private SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("vnsquall", "23c1faf1-4616-4820-bcb8-d9f7b91562f1");

    private WebDriver driver;

    static String test_name = "";
    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "17");
        capabilities.setCapability("platform", Platform.XP);
        capabilities.setCapability("name", "Tutorial App: " + test_name);
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        driver.get("http://tutorialapp.saucelabs.com");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testLogout() throws Exception {
        test_name = "testLogout";
        Map<String, String> userDetails = createRandomUser();
        doRegister(userDetails, true);
    }

    @Test
    public void testLoginFailsWithBadCredentials() throws Exception {
        test_name = "testLoginFailsWithBadCredentials";
        String userName = getUniqueId();
        String password = getUniqueId();
        driver.findElement(By.name("login")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.login")).click();
        assertNotNull(driver.findElement(By.id("message")), "Text not found");
    }

    @Test
    public void testLogin() throws Exception {
        test_name = "testLogin";
        Map<String, String> userDetails = createRandomUser();
        doRegister(userDetails, true);
        doLogin(userDetails.get("username"), userDetails.get("password"));
    }

    @Test
    public void testRegister() throws Exception {
        test_name = "testRegister";
        Map<String, String> userDetails = createRandomUser();
        doRegister(userDetails, false);
        assertTrue(driver.findElement(By.cssSelector(".username")).getText().contains("You are logged in as "), "Message not found");
    }

    @Test
    public void testRegisterFailsWithoutUsername() throws Exception {
        test_name = "testRegisterFailsWithoutUsername";
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("username", "");
        doRegister(userDetails, false);
        assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "Please enter a value", "Message not found");

    }

    @Test
    public void testRegisterFailsWithoutName() throws Exception {
        test_name = "testRegisterFailsWithoutName";
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("name", "");
        doRegister(userDetails, false);
        assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "Please enter a value", "Message not found");
    }

    @Test
    public void testRegisterFailsWithMismatchedPasswords() throws Exception {
        test_name = "testRegisterFailsWithMismatchedPasswords";
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("confirm_password", getUniqueId());
        doRegister(userDetails, false);
        assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "Fields do not match", "Message not found");
    }

    @Test
    public void testRegisterFailsWithBadEmail() throws Exception {
        test_name = "testRegisterFailsWithBadEmail";
        Map<String, String> userDetails = createRandomUser();
        userDetails.put("email", "test");
        doRegister(userDetails, false);
        assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "An email address must contain a single @", "Message not found");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("@example.com");
        driver.findElement(By.id("form.submitted")).click();
        assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "The username portion of the email address is invalid (the portion before the @: )", "Message not found");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("test@example");
        driver.findElement(By.id("form.submitted")).click();
        assertEquals(driver.findElement(By.cssSelector(".error")).getText(), "The domain portion of the email address is invalid (the portion after the @: example)", "Message not found");
    }

    private String getUniqueId() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private void doRegister(Map<String, String> userDetails, boolean logout) {
        userDetails.put("confirm_password", userDetails.get("confirm_password") != null ?
                userDetails.get("confirm_password") : userDetails.get("password"));
        driver.get("http://tutorialapp.saucelabs.com/register");
        driver.findElement(By.id("username")).sendKeys(userDetails.get("username"));
        driver.findElement(By.id("password")).sendKeys(userDetails.get("password"));
        driver.findElement(By.id("confirm_password")).sendKeys(userDetails.get("confirm_password"));
        driver.findElement(By.id("name")).sendKeys(userDetails.get("name"));
        driver.findElement(By.id("email")).sendKeys(userDetails.get("email"));
        driver.findElement(By.id("form.submitted")).click();

        if (logout) {
            doLogout();
        }
    }

    private void doLogout() {
        driver.get("http://tutorialapp.saucelabs.com/logout");
        assertEquals(driver.findElement(By.id("message")).getText(), "Logged out successfully.", "Message not found");
    }

    private Map<String, String> createRandomUser() {
        Map<String, String> userDetails = new HashMap<String, String>();
        String fakeId = getUniqueId();
        userDetails.put("username", fakeId);
        userDetails.put("password", "testpass");
        userDetails.put("name", "Fake " + fakeId);
        userDetails.put("email", fakeId + "@example.com");
        return userDetails;
    }

    private void doLogin(String username, String password) {
        driver.findElement(By.name("login")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input.login")).click();
        assertEquals(driver.findElement(By.id("message")).getText(), "Logged in successfully.", "Message not found");
    }

}