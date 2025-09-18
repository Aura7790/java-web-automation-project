package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='New User Signup!']")
    private WebElement signUpHeading;

    @FindBy(xpath = "//input[@data-qa='signup-name']")
    private WebElement signUpNameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signUpEmailInput;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signUpButton;

    public String getHeadingText() {
        return signUpHeading.getText();
    }

    public void setNameInput(String value) {
        signUpNameInput.sendKeys(value);
    }

    public void setEmailInput(String value){
        signUpEmailInput.sendKeys(value);
    }

    public void clickSignUpButton(){
        signUpButton.click();
    }
}
