package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class HomePageTest extends BaseTest {
    // Use protected loginPage and homePage from BaseTest


    @Test(groups = {"regression"})
    public void testMenuButtonVisibleAfterLogin() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        boolean menuVisible = homePage.isMenuButtonVisible();
        Assert.assertTrue(menuVisible, "Menu button should be visible after login");
    }

    @Test(groups = {"regression"})
    public void testLogout() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        homePage.logout();
        // After logout, check for login button presence
        boolean loginBtnVisible = loginPage.isLoginButtonVisible();
        Assert.assertTrue(loginBtnVisible, "After logout, login button should be visible");
    }

    @Test(groups = {"regression"})
    public void testLogoutFailTest() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        homePage.logout();
        // After logout, check for login button presence
        boolean loginBtnVisible = false;
        Assert.assertTrue(loginBtnVisible, "After logout, login button should be visible");
    }
}
