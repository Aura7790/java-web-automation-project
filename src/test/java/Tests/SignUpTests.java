package Tests;

import Pages.AccountCreationPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SignUpPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.UUID;

public class SignUpTests {
    private WebDriver driver;
    private SignUpPage signUpPage;
    private HomePage homePage;
    private AccountCreationPage accountCreationPage;
    private LoginPage loginPage;

    private final String baseUrl = "https://automationexercise.com/";
    private final String nameInput = "Test17";
    private final String uniqueEmail = "test_" + UUID.randomUUID().toString().substring(0, 6) + "@example.com";
    private final String existentEmail = "test171@gmail.com";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        signUpPage = new SignUpPage(driver);
        homePage = new HomePage(driver);
        accountCreationPage = new AccountCreationPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void signupValidCredentials() {
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        signUpPage.setNameInput(nameInput);
        signUpPage.setEmailInput(uniqueEmail);
        signUpPage.clickSignUpButton();
        Assert.assertEquals(signUpPage.getAccountInfoHeadingText(), "ENTER ACCOUNT INFORMATION");
    }

    @Test
    public void signupWithExistentEmail(){
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        signUpPage.setNameInput(nameInput);
        signUpPage.setEmailInput(existentEmail);
        signUpPage.clickSignUpButton();
        Assert.assertEquals(signUpPage.getExistingEmailErrorText(), "Email Address already exist!");
    }

    @Test
    public void signupWithEmptyName(){
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        signUpPage.setNameInput("");
        signUpPage.setEmailInput(uniqueEmail);
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.getNameValidationMessage().contains("Please fill in this field."));
        String validationMessage = signUpPage.getNameValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
    }

    @Test
    public void signupWithEmptyEmail(){
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        signUpPage.setNameInput(nameInput);
        signUpPage.setEmailInput("");
        signUpPage.clickSignUpButton();
        Assert.assertTrue(signUpPage.getEmailValidationMessage().contains("Please fill in this field."));
        String validationMessage = signUpPage.getEmailValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
    }

    @Test
    public void signupWithInvalidEmailFormat() {
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        signUpPage.setNameInput(nameInput);
        signUpPage.setEmailInput("invalid-email");
        signUpPage.clickSignUpButton();
        String validationMsg = signUpPage.getEmailValidationMessage();
        Assert.assertTrue(validationMsg.toLowerCase().contains("is missing an '@'") || !validationMsg.isEmpty());

    }

    @Test
    public void signupWithValidCredentialsFull(){
        // 1. Navigate to Signup Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Enter name and unique email
        signUpPage.setNameInput(nameInput);
        signUpPage.setEmailInput(uniqueEmail);
        signUpPage.clickSignUpButton();
        // 3. Fill account information
        accountCreationPage.fillAccountInformation("Test@1234");
        // 4. Fill address details
        accountCreationPage.fillAddressDetails();
        // 5. Submit the account form
        accountCreationPage.submitForm();
        // 6. Assert "Account Created!" is displayed
        Assert.assertEquals(accountCreationPage.getAccountCreatedText(), "ACCOUNT CREATED!");
        // 7. Click Continue
        accountCreationPage.clickContinue();
        // 8. Verify user is logged in
        Assert.assertTrue(loginPage.isLoginSuccessful());
        // 9. Logout and verify user logged out with success
        loginPage.isLogoutLinkDisplayed();
        loginPage.logout();
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
    }

    @Test
    public void signupWithCredentialsInsertedInLoginForm() {
        // 1. Navigate to Signup Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Signup Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert valid name and email in the Login form and click Signup button
        loginPage.setEmailInput(uniqueEmail);
        loginPage.setPasswordInput("Password01");
        signUpPage.clickSignUpButton();
        // 4. Verify validation message is shown for Signup name field
        Assert.assertTrue(signUpPage.getNameValidationMessage().contains("Please fill in this field."));
        String validationMessage = signUpPage.getNameValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
