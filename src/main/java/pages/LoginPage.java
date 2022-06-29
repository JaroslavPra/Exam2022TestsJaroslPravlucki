package pages;

import models.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends AbstractObjectPage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='username']")
    public WebElement usernameInputField;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInputField;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-primary btn-block']")
    public WebElement loginButton;
    @FindBy(linkText = "Sukurti naują paskyrą")
    public WebElement registerLink;
    @FindBy(xpath = "//div[@class='form-group has-error']/span[2]")
    public WebElement loginError;
    @FindBy(xpath = "//div[@class='form-group has-error']/span[2]")
    public List<WebElement> loginErrorList;

    public void perfomLoginAcion(Account accountModel){
        usernameInputField.sendKeys(accountModel.getName());
        passwordInputField.sendKeys(accountModel.getPassword());
        loginButton.click();
    }
}