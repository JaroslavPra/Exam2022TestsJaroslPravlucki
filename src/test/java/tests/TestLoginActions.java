package tests;

import models.Account;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utilities.FileReaderUtils;

import java.io.IOException;
import java.util.List;

public class TestLoginActions extends BaseTest{
    private final String LOGGIN_ERROR_MESSAGE = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
    LoginPage loginPage;
    MainPage mainPage;
    /**
     * <p>Test Login Positive</p>
     * <ol>
     * <li>Open web application
     * <li>Check fields for login
     * <li>Fill fields with valid data
     * <li>Click login
     * <li>Check for logout
     * </ol>
     */

    @Test (groups = {"LoginTest"})
    public void testLoginPositive() throws IOException {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/PositiveLoginAccountData.txt");
        Account accountObject = new Account(formData);
        Assert.assertTrue(loginPage.usernameInputField.isEnabled(),"Username input field is not enabled");
        Assert.assertTrue(loginPage.usernameInputField.isDisplayed(), "Username input field is not visible");
        Assert.assertTrue(loginPage.passwordInputField.isEnabled(),"Password input field is not enabled");
        Assert.assertTrue(loginPage.passwordInputField.isDisplayed(), "Password input field is not visible");
        Assert.assertTrue(loginPage.loginButton.isEnabled(),"Login button is not enabled");
        Assert.assertTrue(loginPage.loginButton.isDisplayed(), "Login button is not visible");
        Assert.assertTrue(loginPage.registerLink.isEnabled(),"Register link is not enabled");
        Assert.assertTrue(loginPage.registerLink.isDisplayed(), "Register link is not visible");
        loginPage.perfomLoginAcion(accountObject);
        Assert.assertTrue(mainPage.logoutButton.getText().contains(accountObject.getName()),"Logged username is not displayed, user did not log in");
        Assert.assertTrue(mainPage.logoutButton.isDisplayed(),"Logout button is not displayed, user did not log in");
    }

    /**
     * <p>Test Login Negative</p>
     * <ol>
     * <li>Open web application
     * <li>Fill fields with valid data
     * <li>Click login
     * <li>Check login error message
     * </ol>
     */

    @Test (groups = {"LoginTest"})
    public void testLoginNegative() throws IOException {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/NegativeAccountData.txt");
        Account accountObject = new Account(formData);
        loginPage.perfomLoginAcion(accountObject);

        Assert.assertTrue(elementNotDislayed(mainPage.logoutButtonsList),"Log Out button displayed, user did log in");
        if (loginPage.loginErrorList.size()>0){
            Assert.assertEquals(loginPage.loginError.getText(),LOGGIN_ERROR_MESSAGE,"Expected: "+
                    LOGGIN_ERROR_MESSAGE+" Found: "+loginPage.loginError.getText());
        }
    }

    public boolean elementNotDislayed(List<WebElement> elements){
        return elements.isEmpty();
    }

}
