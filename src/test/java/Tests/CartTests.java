package Tests;

import Pages.CartPage;
import Pages.HomePage;
import Pages.ProductsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class CartTests {
    private WebDriver driver;
    private ProductsPage productsPage;
    private HomePage homePage;
    private CartPage cartPage;

    final String baseUrl = "https://automationexercise.com/";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver.", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        productsPage = new ProductsPage(driver);
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void deleteProductFromCart() throws InterruptedException {
        homePage.clickCookiesConsent();
        productsPage.goToProductsPage();
        productsPage.isProductsPageDisplayed();

        productsPage.scrollAddToCartButtonIntoView();
        productsPage.addProductToCart();
        productsPage.clickContinueShopping();
        productsPage.goToCart();

        cartPage.deleteProductFromCart();
        Thread.sleep(5000);
        Assert.assertTrue("Expected 'Cart is empty!' message.", cartPage.isCartEmptyMessageVisible());
        Assert.assertEquals(cartPage.getCartEmptyText(), "Cart is empty!");
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
