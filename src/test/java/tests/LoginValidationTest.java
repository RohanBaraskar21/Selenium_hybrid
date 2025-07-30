package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import factory.DriverFactory;

public class LoginValidationTest extends BaseTest {

    @Test(groups = {"regression"})
    public void testInvalidLogin() {
        loginPage.login("invalid_user", "wrong_password");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMsg, "Error message should be displayed for invalid login.");
        Assert.assertTrue(errorMsg.contains("Epic sadface"), "Error message should indicate invalid credentials.");
    }

    @Test(groups = {"regression"})
    public void testEmptyCredentials() {
        loginPage.login("", "");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertNotNull(errorMsg, "Error message should be displayed for empty credentials.");
    }
}
