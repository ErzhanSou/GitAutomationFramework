package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage {
    @FindBy(id = "some_id")
    public WebElement checkoutButton;

    @FindBy(name = "name")
    public WebElement name;
}
