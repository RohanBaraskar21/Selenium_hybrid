package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import factory.DriverFactory;

public class InventoryTest extends BaseTest {



    @Test(groups = {"smoke"})
    public void testInventoryItemCount() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        int itemCount = dashboardPage.getItemCount();
        Assert.assertTrue(itemCount > 0, "Inventory should have at least one item.");
    }

    @Test(groups = {"regression"})
    public void testDashboardTitle() {
        loginPage.login(utils.ConfigReader.get("username"), utils.ConfigReader.get("password"));
        Assert.assertTrue(dashboardPage.isAt(), "Dashboard page title should be 'Products'.");
    }
}
