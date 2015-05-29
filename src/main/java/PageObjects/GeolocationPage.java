package PageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import static Utils.Helpers.driver;
import static Utils.Helpers.find_element;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class GeolocationPage {
    public static By geoLink = By.xpath("//*[@id='content']/ul/li[18]/a");
    public static By geoText = By.xpath("//*[@id='content']/div/h3");
    public static By demoText = By.xpath("//*[@id='demo']");
    public static By whereBtn = By.xpath("//*[@id='content']/div/button");

    public static void geoPageLoaded(){
        find_element(geoLink).click();
        Assert.assertEquals(find_element(geoText).getText(), "Geolocation", "Text Not Found");
        Assert.assertEquals(find_element(demoText).getText(), "Click the button to get your current latitude and longitude",
                "Text Not Found");
    }

    public static void click_whereBtn(){
        find_element(whereBtn).click();
        driver.navigate().back();
    }
}
