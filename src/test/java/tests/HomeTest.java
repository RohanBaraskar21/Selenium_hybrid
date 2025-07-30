package tests;

import factory.DriverFactory;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentTestManager;

public class HomeTest extends BaseTest {




    @Test(groups = {"smoke"})
    public void testMenuButtonVisibleAfterLogin() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        boolean menuVisible = homePage.isMenuButtonVisible();
        ExtentTestManager.getTest().info("Menu button visible: " + menuVisible);
        Assert.assertTrue(menuVisible, "Menu button should be visible after login");
    }


}