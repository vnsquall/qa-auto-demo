package TestCases;

import PageObjects.GeolocationPage;
import Utils.SetupTest_SauceLabs;
import org.testng.annotations.Test;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class Geolocation_SauceLabs_Test extends SetupTest_SauceLabs {

    @Test
    public void test_Geolocation(){
        GeolocationPage.geoPageLoaded();
        GeolocationPage.click_whereBtn();
    }
}
