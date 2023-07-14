package qtriptest.pages;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
    public String lastGeneratedUserName;

 

    @FindBy(id="floatingInput")
    WebElement emailOfUser;

    @FindBy(id="floatingPassword")
    WebElement setPassword;

    @FindBy(name="confirmpassword")
    WebElement confrimPassword;

    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement registerBtn;

    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory ajax = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(ajax, this);
    }
    
    // public void navigateToRegisterPage() {
    //     if(this.driver.getCurrentUrl()!=url){
    //       this.driver.get(url);
    //     }
    //   }

    public boolean performRegister(String email, String password, boolean makeDynamicUsername){
        //click on register page
        
        String dynamic_username="";
        //Timestamp time = new Timestamp(System.currentTimeMillis());
        if(makeDynamicUsername){
            dynamic_username = email+"_"+ UUID.randomUUID().toString() +"@gmail.com";
        }else{
           dynamic_username = email;
        }

        emailOfUser.sendKeys(dynamic_username);
        setPassword.sendKeys(password);
        confrimPassword.sendKeys(password);
        registerBtn.click();
        
    //    WebDriverWait wait = new WebDriverWait(driver, 30);
    // wait.until(ExpectedConditions.or(
    //     ExpectedConditions.urlToBe("https://qtripdynamic-qa-frontend.vercel.app/pages/login/")));

        this.lastGeneratedUserName = dynamic_username;
        return this.driver.getCurrentUrl().endsWith("/login");

    }
}
