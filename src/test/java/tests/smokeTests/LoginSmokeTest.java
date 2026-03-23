package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeClass
    public void pageSetUp() {
       loginPage = new LoginPage(driver);
    }
}
