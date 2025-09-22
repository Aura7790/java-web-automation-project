package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//a[@href='/products']")
    private WebElement productLink;

    @FindBy(className= "features_items")
    private WebElement allProducts;

    @FindBy(xpath = "//h2[text()='All Products']")
    private WebElement allProductsText;

    @FindBy(id= "search_product")
    private WebElement searchInputField;

    @FindBy(id = "submit_search")
    private WebElement searchButton;

    @FindBy(xpath = "//h2[text()='Searched Products'")
    private WebElement searchedProductsHeader;

    @FindBy(xpath = "//a[contains(text(), 'View Product')]")
    private WebElement firstViewProductLink;

    @FindBy(xpath = "//div[@class='product-information']")
    private WebElement productDetailsSection;

    @FindBy(xpath = "//div[@class='left-sidebar']/h2[text()='Category']")
    private WebElement categorySection;

    @FindBy(className = "shop-details-tab")
    private WebElement reviewSection;

    @FindBy(xpath = "(//a[contains(text(),'Add to cart')])[1]")
    private WebElement firstAddToCartButton;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//a[@href='/view_cart']")
    private WebElement viewCartLink;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> cartItems;

    public void goToProductsPage(){
        productLink.click();
    }

    public boolean isProductsPageDisplayed(){
        return allProducts.isDisplayed();
    }

    public boolean isAllProductsTextDisplayed(){
        return allProductsText.isDisplayed();
    }

    public void clickSearchField(){
        searchInputField.click();
    }

    public void clearSearchField(){
        searchInputField.clear();
    }

    public void setInputSearchField(String searchInput){
        searchInputField.sendKeys(searchInput);
    }

    public void clickSearchButton(){
        searchButton.click();
    }

    public boolean isSearchedProductsPageDisplayed(){
        return searchedProductsHeader.isDisplayed();
    }

    public void scrollProductElementIntoView(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstViewProductLink);
    }

    public void clickViewProductLink(){
        firstViewProductLink.click();
    }

    public boolean isProductDetailsDisplayed(){
        return productDetailsSection.isDisplayed();
    }

    public boolean isCategorySectionDisplayed(){
        return categorySection.isDisplayed();
    }

    public boolean isReviewSectionDisplayed(){
        return reviewSection.isDisplayed();
    }

    public void scrollAddToCartButtonIntoView(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstAddToCartButton);
    }

    public void addProductToCart(){
        firstAddToCartButton.click();
    }

    public void clickContinueShopping(){
        continueShoppingButton.click();
    }

    public void scrollCartIntoView(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewCartLink);
    }

    public void goToCart(){
        viewCartLink.click();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }
}
