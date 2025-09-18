package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationPage {
    private WebDriver driver;

    public AccountCreationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "id_gender1")
    private WebElement titleMr;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daysDropdown;

    @FindBy(id = "months")
    private WebElement monthsDropdown;

    @FindBy(id = "years")
    private WebElement yearsDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "address2")
    private WebElement address2;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "mobile_number")
    private WebElement mobile;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//b[text()='Account Created!']")
    private WebElement accountCreatedMessage;

    @FindBy(xpath = "//a[@data-qa='continue-button']")
    private WebElement continueButton;

    public void fillAccountInformation(String password) {
        titleMr.click();
        passwordInput.sendKeys(password);

        new Select(daysDropdown).selectByVisibleText("22");
        new Select(monthsDropdown).selectByVisibleText("July");
        new Select(yearsDropdown).selectByVisibleText("1999");

        newsletterCheckbox.click();
        specialOffersCheckbox.click();
    }

    public void fillAddressDetails() {
        firstName.sendKeys("Test");
        lastName.sendKeys("Test12");
        company.sendKeys("Example Ex.");
        address1.sendKeys("1234 Street");
        address2.sendKeys("12");

        new Select(country).selectByVisibleText("United States");

        state.sendKeys("California");
        city.sendKeys("Los Angeles");
        zipcode.sendKeys("2004");
        mobile.sendKeys("456789");
    }

    public void submitForm() {
        createAccountButton.click();
    }

    public String getAccountCreatedText() {
        return accountCreatedMessage.getText();
    }

    public void clickContinue() {
        continueButton.click();
    }
}
