package tests;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import utils.ExtentTestManager;

public class LoginTest extends BaseTest {

    protected LoginPage loginPage;
    // Use protected homePage from BaseTest

    @BeforeMethod
    public void setUp() {
        DriverFactory.setDriver();
        DriverFactory.getDriver().get(utils.ConfigReader.get("url"));
        loginPage = new LoginPage(DriverFactory.getDriver());
        // homePage is initialized in BaseTest
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
            {"standard_user", "secret_sauce"},
            {"problem_user", "secret_sauce"},
            // Add more test data as needed
        };
    }

    @Test(groups = {"regression"}, dataProvider = "loginData")
    public void testValidLogin(String username, String password) {
        loginPage = new pages.LoginPage(driver);
        loginPage.login(username, password);
        ExtentTestManager.getTest().info("Logged in with user: " + username);
        Assert.assertTrue(homePage.isMenuButtonVisible(), "Menu button should be visible after login");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}