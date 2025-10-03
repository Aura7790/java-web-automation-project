package Tests;

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

public class LoginTests {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    private final String baseUrl = "https://automationexercise.com/";
    private final String validEmail = "test171@gmail.com";
    private final String validPassword = "test1234#";
    private final String notRegisteredEmail = "bla1712@yahoo.com";
    private final String wrongPassword = "wrong password";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void loginWithValidData() {
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert valid email and password and clicks Login button
        loginPage.setEmailInput(validEmail);
        loginPage.setPasswordInput(validPassword);
        loginPage.clickLoginButton();
        // 4. Verify Login successful
        Assert.assertTrue(loginPage.isLoginSuccessful());
        // 5. Logout and verify user logged out with success
        loginPage.isLogoutLinkDisplayed();
        loginPage.logout();
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");

    }

    @Test
    public void loginWithNotRegisteredUser() {
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert not registered email and password and click Login button
        loginPage.setEmailInput(notRegisteredEmail);
        loginPage.setPasswordInput(validPassword);
        loginPage.clickLoginButton();
        // 4. Verify Login fails
        Assert.assertTrue(loginPage.isLoginFailed());
    }

    @Test
    public void loginWithWrongPassword() {
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert valid email and wrong password and click Login button
        loginPage.setEmailInput(validEmail);
        loginPage.setPasswordInput(wrongPassword);
        loginPage.clickLoginButton();
        // 4. Verify Login fails
        Assert.assertTrue(loginPage.isLoginFailed());
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
    }

    @Test
    public void loginWithEmptyPassword() {
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert valid email and empty password and click Login button
        loginPage.setEmailInput(validEmail);
        loginPage.setPasswordInput("");
        loginPage.clickLoginButton();
        // 4. Verify validation message is shown for password field
        String validationMessage = loginPage.getPasswordValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
    }

    @Test
    public void loginWithEmptyEmail() {
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert empty email and valid password and click Login button
        loginPage.setEmailInput("");
        loginPage.setPasswordInput(validPassword);
        loginPage.clickLoginButton();
        // 4. Verify validation message is shown for email field
        String validationMessage = loginPage.getEmailValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
    }

    @Test
    public void loginWithEmptyCredentials(){
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert empty email and password and click Login button
        loginPage.setEmailInput("");
        loginPage.setPasswordInput("");
        loginPage.clickLoginButton();
        // 4. Verify validation message is shown for email field
        String validationMessage = loginPage.getEmailValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
    }

    @Test
    public void loginInvalidEmailFormat(){
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert invalid email format
        loginPage.setEmailInput("test"); // test171@
        loginPage.clickLoginButton();
        // 4. Verify validation message is shown for email field
        String validationMsg = loginPage.getEmailValidationMessage();
        Assert.assertTrue(validationMsg.toLowerCase().contains("is missing an '@'") || !validationMsg.isEmpty());
    }

    @Test
    public void loginInvalidEmailFormat2(){
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert invalid email format
        loginPage.setEmailInput("test171@");
        loginPage.clickLoginButton();
        // 4. Verify validation message is shown for email field
        String validationMsg = loginPage.getEmailValidationMessage();
        Assert.assertTrue(validationMsg.toLowerCase().contains("'test171@' is incomplete") || !validationMsg.isEmpty());
    }

    @Test
    public void loginWithCredentialsInsertedInSignUpForm(){
        // 1. Navigate to Login Page
        homePage.clickCookiesConsent();
        homePage.clickLoginSignupLink();
        // 2. Wait for Login Modal to display
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
        // 3. Insert valid name and email in the signUp form and click Login button
        signUpPage.setNameInput("Test_Test");
        signUpPage.setEmailInput(validEmail);
        loginPage.clickLoginButton();
        // 4. Verify validation message is shown for Login email field
        String validationMessage = loginPage.getEmailValidationMessage();
        Assert.assertFalse(validationMessage.isEmpty());
        Assert.assertEquals(loginPage.getHeadingText(), "Login to your account");
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
