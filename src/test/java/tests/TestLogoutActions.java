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

public class TestLogoutActions extends BaseTest{
    RegisterPage registerPage;
    LoginPage loginPage;
    MainPage mainPage;

    /**
     * <p>Test Register</p>
     * <ol>
     * <li>Open web application
     * <li>Navigate to register page
     * <li>Fill fields with valid data
     * <li>Click register
     * <li>Check lougout button
     * <li>Click logout button
     * <li>Check for login page
     * </ol>
     */

    @Test (groups = {"LogoutTest"})
    public void testLogoutPositive() throws IOException {
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        List<String> formData = FileReaderUtils.getTestData("src/test/resources/PositiveLogOutData.txt");
        Account accountObject = new Account(formData);
        loginPage.registerLink.click();
        registerPage.perfomRegisterAcion(accountObject);
        Assert.assertTrue(mainPage.logoutButton.isDisplayed(),"Logout button is not displayed");
        mainPage.logoutButton.click();
        Assert.assertTrue(elementNotDislayed(mainPage.logoutButtonsList),"After performing logout, logout button still exists");
        Assert.assertTrue(loginPage.loginButton.isDisplayed(),"Login button is not dispalyed");

    }

    public boolean elementNotDislayed(List<WebElement> elements){
        return elements.isEmpty();
    }

}
