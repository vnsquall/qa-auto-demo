package TestCases;

import Features.GeolocationFeature;
import org.testng.annotations.Test;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class GeolocationTest extends GeolocationFeature {
    @Test
    public void test_Geolocation(){
        geoLocationLoadedAndClickGetLocation();
    }
}
