package qtriptest.tests;

import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;

public class TestCases {
    static RemoteWebDriver driver;
    
    public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

    @BeforeClass(alwaysRun = true, enabled = false)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver", "Success");
		driver.manage().window().maximize();
	}

    @Test(description="Verify user navigate to Home page", enabled =false)
    public static void homePageNavigation(){
        Assertion assertion = new Assertion();
        logStatus("Page test", "navigation to Home page", "started");
        try{
        HomePage homePage = new HomePage(driver);
        homePage.navigateToPageUrl();
        driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/");
        logStatus("Page test", "navigation to Home page", "success");
        }catch(Exception e){
            logStatus("Page test", "navigation to Home page", "failed"); 
            e.printStackTrace();
        }
    }

    @Test(description="Verify user navigate to Register page click upon Register Page", enabled = false)
    public static void registerPageNavigation(){
        Assertion assertion = new Assertion();
        logStatus("Page test", "navigation to Register page", "started");
        try{
            HomePage homePage = new HomePage(driver);
            homePage.navigateToRegisterPage();
            driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
            logStatus("Page test", "navigation to Register page", "success");
        }catch(Exception e){
            logStatus("Page test", "navigation to Register page", "failed"); 
            e.printStackTrace();
        }
    }

    @Test(description = "Verify the functionality registration", enabled = false)
    public static void test_performRegister(){
        Assertion assertion = new Assertion();
        logStatus("Page test", "Perform register", "started");
        try{
            HomePage homePage = new HomePage(driver);
            homePage.navigateToPageUrl();
            homePage.navigateToRegisterPage();
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.performRegister("Test", "Test@123", true);
            logStatus("Page test", "Perform registration", "success");
        }catch (Exception e) {
			logStatus("Page test", "Perform registration ", "failed");
			e.printStackTrace();
		}
    }

    @Test(description = "Verify login funtionality - Login Test",enabled = false)
    public static void test_performLogin(){
        Assertion assertion = new Assertion();
        logStatus("Page test", "Perform Login", "started");
        try{
            LoginPage login = new LoginPage(driver);
            login.navigateToLoginPage();
            login.performLogin("Test12345@gmail.com", "Test@123");
            logStatus("Page test", "Perform Login", "success");
            logStatus("Page test", "Verify user successfully login", "started");
            HomePage homePage = new HomePage(driver);
            assertion.assertTrue(homePage.userSuccessLogin());
        }catch(Exception e){
            logStatus("Page test", "Perform Login ", "failed");
			e.printStackTrace();
        }
    }
    
    @Test(description = "Verify the Logout functionality -Logout", enabled = false, dependsOnMethods = "test_performLogin")
    public static void userLogOut(){
        logStatus("Page test", "Perform Logout", "started");
        try{
            HomePage homePage = new HomePage(driver);
            homePage.performLogOut();
            logStatus("Page test", "Perform Logout", "success");
        }catch(Exception e){
            logStatus("Page test", "Perform Logout ", "failed");
            e.printStackTrace();
        }
    }

    @AfterClass(enabled = false)
	public static void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		logStatus("driver", "Quitting driver", "Success");
	}
  


}
