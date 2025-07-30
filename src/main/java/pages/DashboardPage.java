package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(className = "inventory_item")
    protected List<WebElement> inventoryItems;

    @FindBy(className = "title")
    protected WebElement pageTitle;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAt() {
        waitForVisibility(pageTitle);
        return pageTitle.isDisplayed() && getText(pageTitle).equalsIgnoreCase("Products");
    }

    public int getItemCount() {
        return inventoryItems.size();
    }
}