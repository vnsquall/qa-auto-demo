package TestCases;

import PageObjects.BasicAuthPage;
import Utils.SetupTest;
import org.testng.annotations.Test;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class BasicAuthTest extends SetupTest {

    @Test
    public void test_BasicAuthPage(){
        BasicAuthPage.basicAuthLoaded();
        BasicAuthPage.basicAuthLogin();
    }
}
