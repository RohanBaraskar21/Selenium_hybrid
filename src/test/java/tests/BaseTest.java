package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;
import factory.DriverFactory;

import java.lang.reflect.Method;

public abstract class BaseTest {
    protected static ExtentReports extent;
    protected WebDriver driver;
    // Common page objects (extend as needed)
    protected pages.LoginPage loginPage;
    protected pages.DashboardPage dashboardPage;
    protected pages.HomePage homePage;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() {
        extent = ExtentManager.getExtentReports();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        extent.flush();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest(Method method) {
        // WebDriver setup
        DriverFactory.setDriver();
        driver = DriverFactory.getDriver();
        driver.get(utils.ConfigReader.get("url"));
        // Page object initialization (extend as needed)
        loginPage = new pages.LoginPage(driver);
        dashboardPage = new pages.DashboardPage(driver);
        homePage = new pages.HomePage(driver);
        // Reporting
        ExtentTest test = extent.createTest(method.getName());
        test.assignCategory(this.getClass().getSimpleName());
        test.assignAuthor(System.getProperty("user.name"));
        ExtentTestManager.setTest(test);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownTest(ITestResult result) {
        String screenshotPath = null;
        if (result.getStatus() == ITestResult.FAILURE) {
            screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                ExtentTestManager.getTest().fail("Test Failed. Screenshot below:")
                    .addScreenCaptureFromPath(screenshotPath);
            } else {
                ExtentTestManager.getTest().fail("Test Failed. No screenshot available.");
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                ExtentTestManager.getTest().skip("Test Skipped. Screenshot below:")
                    .addScreenCaptureFromPath(screenshotPath);
            } else {
                ExtentTestManager.getTest().skip("Test Skipped. No screenshot available.");
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().pass("Test Passed.");
        }
        ExtentTestManager.removeTest();
        DriverFactory.quitDriver();
    }
}