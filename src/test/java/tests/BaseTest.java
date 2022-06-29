package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun=true)
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }
    @BeforeMethod(alwaysRun=true)
    public void openHomePage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
    }
    @AfterMethod(alwaysRun=true)
    public static void closeBrowser(){
        driver.close();
    }

    @AfterSuite(alwaysRun=true)
    public static void quiteDriver(){
        driver.quit();
    }
}