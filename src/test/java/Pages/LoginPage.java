package Pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Login to your account']")
    private WebElement loginHeading;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInText;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutLink;

    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    private WebElement failedLogin;

    public String getHeadingText() {
        return loginHeading.getText();
    }

    public void setEmailInput(String value) {
        emailInput.sendKeys(value);
    }

    public void setPasswordInput(String value){
        passwordInput.sendKeys(value);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public boolean isLoginSuccessful() {
        return loggedInText.isDisplayed();
    }

    public boolean isLogoutLinkDisplayed() {
        try {
            return logoutLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        logoutLink.click();
    }

    public boolean isLoginFailed(){
        return failedLogin.isDisplayed();
    }

    public String getPasswordValidationMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", passwordInput);
    }

    public String getEmailValidationMessage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", emailInput);
    }

}
