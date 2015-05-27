package Features;
import PageObjects.BasicAuthPage;
import Utils.SetupTest;

/**
 * Created by khanh.nguyen on 5/27/2015.
 */
public class BasicAuthFeature extends SetupTest {
    protected void basic_login(){
        BasicAuthPage.basicAuthLogin();
    }
}
