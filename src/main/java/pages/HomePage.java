package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // SauceDemo: menu button (burger menu) and logout link
    @FindBy(id = "react-burger-menu-btn")
    protected WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    protected WebElement logoutLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isMenuButtonVisible() {
        try {
            waitForVisibility(menuButton);
            return menuButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }
}