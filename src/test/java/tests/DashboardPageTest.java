package tests;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DashboardPageTest extends BaseTest {




    @Test(groups = {"smoke"})
    public void testProductsDisplayed() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        Assert.assertTrue(dashboardPage.isAt(), "Should be on products dashboard.");
        Assert.assertTrue(dashboardPage.getItemCount() > 0, "Inventory items should be displayed.");
    }


}