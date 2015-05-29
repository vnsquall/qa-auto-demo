package TestCases;

import PageObjects.GeolocationPage;
import Utils.SetupTest;
import org.testng.annotations.Test;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class GeolocationTest extends SetupTest {

    @Test
    public void test_Geolocation(){
        GeolocationPage.geoPageLoaded();
        GeolocationPage.click_whereBtn();
    }
}
