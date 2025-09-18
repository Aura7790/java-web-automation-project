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
import java.util.UUID;

public class SignUpTests {
    private WebDriver driver;
    SignUpPage signUpPage;
    HomePage homePage;

    String baseUrl = "https://automationexercise.com/";
    String nameInput = "Test17";
    String uniqueEmail = "test_" + UUID.randomUUID().toString().substring(0, 6) + "@example.com";
    String existentEmail = "test171@gmail.com";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        signUpPage = new SignUpPage(driver);
        homePage = new HomePage(driver);
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

    @After
    public void tearDown(){
        driver.close();
    }
}
