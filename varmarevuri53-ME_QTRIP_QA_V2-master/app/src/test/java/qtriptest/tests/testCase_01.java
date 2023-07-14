package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class testCase_01 {
    static RemoteWebDriver driver;

    public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

    @BeforeClass(alwaysRun = true, enabled = true)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver", "Success");
		driver.manage().window().maximize();
	}

	@Test(description ="Implement the test case01 of Qtrip Application", enabled = true, dataProvider = "QTripData",dataProviderClass = DP.class )
	public static void TestCase01(String UserName, String Password){
		try{
			logStatus("Test Cae01", "Verifying the test case01 funtionality", "started");
		HomePage homePage = new HomePage(driver);
		homePage.navigateToPageUrl();
		homePage.navigateToRegisterPage();
		RegisterPage register = new RegisterPage(driver);
		register.performRegister("UserName", "Password", true);		
		LoginPage loginPage = new LoginPage(driver);
		Thread.sleep(3000);
		loginPage.performLogin(UserName, Password);
		homePage.userSuccessLogin();
		homePage.performLogOut();
		homePage.successfullLogOut();
		logStatus("Test Cae01", "Verifying the test case01 funtionality", "Success");
		}
		catch(Exception e){
			logStatus("Test Cae01", "Verifying the test case01 funtionality", "Failed");
			e.printStackTrace();
		}
	}

	@AfterClass(enabled = true)
	public static void quitDriver() throws MalformedURLException {
		driver.close();
		driver.quit();
		logStatus("driver", "Quitting driver", "Success");
	}
  

}
