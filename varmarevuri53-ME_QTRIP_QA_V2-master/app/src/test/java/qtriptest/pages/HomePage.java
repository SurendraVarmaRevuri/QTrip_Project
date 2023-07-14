package qtriptest.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;
    String url ="https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerPageBtn;
    @FindBy(xpath ="//div[text()='Logout']")
    WebElement logOutBtn;

    public HomePage(RemoteWebDriver driver){
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajax, this);
        this.driver =driver;
    }

    public void navigateToPageUrl(){
        if(driver.getCurrentUrl() != url ){
            driver.get(this.url);
        }
    }

    public boolean  navigateToRegisterPage(){
        registerPageBtn.click();
        return this.driver.getCurrentUrl().endsWith("/register");
    }
    
    public boolean userSuccessLogin(){
        if(logOutBtn.isDisplayed()){
            System.out.println("Able to view Log Out button -Successfull of login ");
            return true;
        }else{
            System.out.println("Unable to view Log Out button -Failed to login ");
            return false;
        }
        
    }

    public void performLogOut(){
        logOutBtn.click();
    }
    
    public boolean successfullLogOut(){
        if(registerPageBtn.isDisplayed()){
            System.out.println("Able to view Register button -Successfull of Log out ");
            return true;
        }else{
            System.out.println("Unable to view Register button -Failed to Log out");
            return false;
        }
    }

}
