package PageObjects;

import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;
import static Utils.Helpers.driver;
import static Utils.Helpers.find_element;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class BasicAuthPage {
    public static By basicAuthLink = By.cssSelector("a[href='/basic_auth']");
    public static By congratText = By.cssSelector("p");

    public static void basicAuthLoaded(){
        find_element(basicAuthLink).click();
    }

    public static void basicAuthLogin() {
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        assertEquals(find_element(congratText).getText(),
                "Congratulations! You must have the proper credentials.",
                "Congrats Text NOT FOUND!");
    }
}
