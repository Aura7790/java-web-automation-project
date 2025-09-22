package Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private WebElement deleteIcon;

    @FindBy(xpath = "//*[@id='empty_cart']/p/b")
    private WebElement emptyCartMessage;

    public void deleteProductFromCart() {
        deleteIcon.click();
    }

    public boolean isCartEmptyMessageVisible() {
        try {
            return emptyCartMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getCartEmptyText(){
        return emptyCartMessage.getText();
    }
}
