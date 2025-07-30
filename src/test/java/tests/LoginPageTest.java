package tests;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest {

    @DataProvider(name = "invalidCreds")
    public Object[][] invalidCreds() {
        return new Object[][]{
            {"", ""},
            {"locked_out_user", utils.ConfigReader.get("password")},
            {utils.ConfigReader.get("username"), ""},
            {"", utils.ConfigReader.get("password")},
            {"wrong_user", "wrong_pass"}
        };
    }

    @Test(groups = {"smoke"})
    public void testValidLogin() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        Assert.assertTrue(dashboardPage.isAt(), "Dashboard should be visible after login.");
    }

    @Test(groups = {"regression"}, dataProvider = "invalidCreds")
    public void testInvalidLogin(String username, String password) {
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().length() > 0, "Should show error message for invalid credentials.");
    }


}