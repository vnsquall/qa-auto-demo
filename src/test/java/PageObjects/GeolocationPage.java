package PageObjects;

import org.openqa.selenium.By;

import static Utils.Helpers.find_element;
import static org.testng.Assert.assertEquals;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class GeolocationPage {
    public static By geoLink = By.cssSelector("//*[@id=\"content\"]/ul/li[18]/a");
    public static By geoText = By.xpath("//*[@id=\"content\"]/div/h3");
    public static By demoText = By.xpath("//*[@id=\"demo\"]");
    public static By whereBtn = By.xpath("//*[@id=\"content\"]/div/button");

    public static void geoPageLoaded(){
        find_element(geoLink).click();
        assertEquals(find_element(geoText).getText(), "Geolocation", "Text Not Found");
        assertEquals(find_element(demoText).getText(), "Click the button to get your current latitude and longitude",
                "Text Not Found");
    }

    public static void click_whereBtn(){
        find_element(whereBtn).click();
    }
}
