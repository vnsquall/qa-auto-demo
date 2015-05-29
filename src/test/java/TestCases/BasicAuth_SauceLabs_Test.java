package TestCases;

import PageObjects.BasicAuthPage;
import Utils.SetupTest_SauceLabs;
import org.testng.annotations.Test;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class BasicAuth_SauceLabs_Test extends SetupTest_SauceLabs {

    @Test
    public void test_BasicAuthPage(){
        BasicAuthPage.basicAuthLogin();
        BasicAuthPage.basicAuthLoaded();
    }
}
