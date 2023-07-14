package qtriptest.pages;

import qtriptest.DP;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Test;

public class LoginPage {

    RemoteWebDriver driver;
    @FindBy(id="floatingInput")
    WebElement emailOfUser;

    @FindBy(id="floatingPassword")
    WebElement setPassword;

    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement loginBtn;

    String url ="https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    public LoginPage(RemoteWebDriver driver){
        this.driver =driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver,10);
        PageFactory.initElements(ajax, this);
    }

    public void navigateToLoginPage() {
        if (this.driver.getCurrentUrl() != url) {
            this.driver.get(url);
        }
    }

    public void performLogin( String userName, String password)throws InterruptedException{
        emailOfUser.sendKeys(userName);
        setPassword.sendKeys(password);
        loginBtn.click();
    }
}
