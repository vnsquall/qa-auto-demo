package Features;

import PageObjects.GeolocationPage;
import Utils.SetupTest;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class GeolocationFeature extends SetupTest {
    protected void geoLocationLoadedAndClickGetLocation(){
        GeolocationPage.geoPageLoaded();
        GeolocationPage.click_whereBtn();
    }
}
