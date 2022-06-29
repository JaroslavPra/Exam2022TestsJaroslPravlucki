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

public class TestRegisterActions extends BaseTest {

    private final String ERROR_SHORT_NAME = "Privaloma įvesti nuo 3 iki 32 simbolių";
    private final String ERROR_SHORT_PASSWORD = "Privaloma įvesti bent 3 simbolius";
    private final String ERROR_NOT_SAME_PSW = "Įvesti slaptažodžiai nesutampa";
    private final String ERROR_NAME_EXIST = "Toks vartotojo vardas jau egzistuoja";


    RegisterPage registerPage;
    LoginPage loginPage;
    MainPage mainPage;

    /**
     * <p>Test Register</p>
     * <ol>
     * <li>Open web application
     * <li>Navigate to register page
     * <li>Check fields
     * <li>Fill fields with valid data
     * <li>Click register
     * </ol>
     */

    @Test (groups = {"RegisterTest"})
    public void testRegisterPositive() throws IOException {
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/PositiveRegisterAccountData.txt");
        Account accountObject = new Account(formData);
        loginPage.registerLink.click();
        Assert.assertTrue(registerPage.usernameInputField.isEnabled(),"Username input field is not enabled");
        Assert.assertTrue(registerPage.usernameInputField.isDisplayed(), "Username input field is not visible");
        Assert.assertTrue(registerPage.passwordInputField.isEnabled(),"Password input field is not enabled");
        Assert.assertTrue(registerPage.passwordInputField.isDisplayed(), "Password input field is not visible");
        Assert.assertTrue(registerPage.passwordConfirmField.isEnabled(),"Password input field is not enabled");
        Assert.assertTrue(registerPage.passwordConfirmField.isDisplayed(), "Password input field is not visible");
        Assert.assertTrue(registerPage.registerButton.isEnabled(),"Register button is not enabled");
        Assert.assertTrue(registerPage.registerButton.isDisplayed(), "Register button is not visible");
        registerPage.perfomRegisterAcion(accountObject);
        Assert.assertTrue(mainPage.logoutButton.isDisplayed(),"Logout button is not displayed");
    }

    /**
     * <p>Test Register</p>
     * <ol>
     * <li>Open web application
     * <li>Navigate to register page
     * <li>Fill fields with invalid data
     * <li>Click register
     * <li>Check error messages
     * <li>Create user
     * <li>Logout and navigate to register page
     * <li>Fill register with same username
     * <li>Check for error message
     * </ol>
     */

    @Test (groups = {"RegisterTest"})
    public void testRegisterNegative() throws IOException {
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/NegativeAccountData.txt");
        Account accountObject = new Account(formData);
        List<String> validAccountData = FileReaderUtils.getTestData("src/test/resources/ValidNegativeAccountData.txt");
        Account validAccount = new Account(validAccountData);
        loginPage.registerLink.click();
        registerPage.perfomRegisterAcion(accountObject);

        Assert.assertTrue(elementNotDislayed(mainPage.logoutButtonsList),"Logout button is visible, user did log in after registration");

        if (elementNotDislayed(mainPage.logoutButtonsList)){

            Assert.assertEquals(registerPage.usernameFieldError.getText(),ERROR_SHORT_NAME,"Expected: "+
                    ERROR_SHORT_NAME+" Found: "+registerPage.usernameFieldError.getText());
            Assert.assertEquals(registerPage.passwordFieldError.getText(),ERROR_SHORT_PASSWORD,"Expected: "+
                    ERROR_SHORT_PASSWORD+" Found: "+registerPage.passwordFieldError.getText());

            registerPage.passwordConfirmField.sendKeys("randomPSW123");
            registerPage.registerButton.click();

            Assert.assertEquals(registerPage.passwordConfirmFieldError.getText(),ERROR_NOT_SAME_PSW,"Expected: "+
                    ERROR_NOT_SAME_PSW+" Found: "+registerPage.passwordConfirmFieldError.getText());

            registerPage.perfomRegisterAcion(validAccount);
            mainPage.logoutButton.click();
            loginPage.registerLink.click();
            registerPage.perfomRegisterAcion(validAccount);

            Assert.assertEquals(registerPage.usernameFieldError.getText(),ERROR_NAME_EXIST,"Expected: "+
                    ERROR_NAME_EXIST+" Found: "+registerPage.usernameFieldError.getText());

        }

    }

    public boolean elementNotDislayed(List<WebElement> elements){
        return elements.isEmpty();
    }

}