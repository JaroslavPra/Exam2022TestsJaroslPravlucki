package pages;

import models.Account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RegisterPage extends AbstractObjectPage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    public WebElement usernameInputField;
    @FindBy(id = "password")
    public WebElement passwordInputField;
    @FindBy(id = "passwordConfirm")
    public WebElement passwordConfirmField;
    @FindBy(xpath = "//button[@class='btn btn-lg btn-primary btn-block']")
    public WebElement registerButton;

    @FindBy(id = "username.errors")
    public WebElement usernameFieldError;
    @FindBy(id = "password.errors")
    public WebElement passwordFieldError;
    @FindBy(id = "passwordConfirm.errors")
    public WebElement passwordConfirmFieldError;

    @FindBy(id = "username.errors")
    public List<WebElement> usernameFieldErrorList;
    @FindBy(id = "password.errors")
    public List<WebElement> passwordFieldErrorList;
    @FindBy(id = "passwordConfirm.errors")
    public List<WebElement> passwordConfirmFieldErrorList;



    public void perfomRegisterAcion(Account accountModel){
        usernameInputField.sendKeys(accountModel.getName());
        passwordInputField.sendKeys(accountModel.getPassword());
        passwordConfirmField.sendKeys(accountModel.getPassword());
        registerButton.click();
    }

}
