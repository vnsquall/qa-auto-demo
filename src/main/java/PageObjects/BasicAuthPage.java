package PageObjects;

import Utils.Helpers;
import org.testng.Assert;
import org.openqa.selenium.By;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class BasicAuthPage {
    public static By basicAuthLink = By.cssSelector("a[href='/basic_auth']");
    public static By congratText = By.cssSelector("p");

    public static void basicAuthLoaded(){
        Helpers.find_element(basicAuthLink).click();
    }

    public static void basicAuthLogin() {
        Helpers.driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(Helpers.find_element(congratText).getText(),
                "Congratulations! You must have the proper credentials.",
                "Congrats Text NOT FOUND!");
    }
}
