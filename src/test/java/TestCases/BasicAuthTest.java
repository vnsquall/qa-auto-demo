package TestCases;

import org.testng.annotations.Test;
import Features.BasicAuthFeature;

import static PageObjects.BasicAuthPage.basicAuthLogin;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class BasicAuthTest extends BasicAuthFeature {
    @Test
    public void test_BasicAuthPage(){
        basicAuthLogin();
    }
}
