package Tests;

import Pages.HomePage;
import Pages.ProductsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ProductsTests {
    private WebDriver driver;
    private ProductsPage productsPage;
    private HomePage homePage;

    final String baseUrl = "https://automationexercise.com/";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver.", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        productsPage = new ProductsPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void navigateToProductsPage(){
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        Assert.assertTrue(productsPage.isProductsPageDisplayed());

    }

    @Test
    public void checkProductsAreListed(){
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        productsPage.isProductsPageDisplayed();
        Assert.assertTrue(productsPage.isAllProductsTextDisplayed());
    }

    @Test
    public void searchForAProductAndCheckResult(){
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        productsPage.isProductsPageDisplayed();

        productsPage.clickSearchField();
        productsPage.clearSearchField();
        productsPage.setInputSearchField("top");
        productsPage.clickSearchButton();
        Assert.assertTrue(productsPage.isSearchedProductsPageDisplayed());
    }

    @Test
    public void checkProductDetails(){
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        productsPage.isProductsPageDisplayed();

        productsPage.scrollProductElementIntoView();
        productsPage.clickViewProductLink();
        Assert.assertTrue(productsPage.isProductDetailsDisplayed());
        Assert.assertTrue(productsPage.isCategorySectionDisplayed());
    }

    @Test
    public void checkReviewSectionIsDisplayed(){
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        productsPage.isProductsPageDisplayed();

        productsPage.scrollProductElementIntoView();
        productsPage.clickViewProductLink();
        Assert.assertTrue(productsPage.isReviewSectionDisplayed());
    }

    @Test
    public void addItemToCart(){
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        productsPage.isProductsPageDisplayed();

        productsPage.scrollAddToCartButtonIntoView();
        productsPage.addProductToCart();
        productsPage.clickContinueShopping();

        productsPage.scrollCartIntoView();
        productsPage.goToCart();
        Assert.assertTrue(productsPage.getCartItemCount() > 0);
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
