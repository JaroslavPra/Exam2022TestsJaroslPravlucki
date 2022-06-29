package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends AbstractObjectPage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "logoutForm")
    public WebElement logoutButton;

    @FindBy(id = "logoutForm")
    public List<WebElement> logoutButtonsList;
}
