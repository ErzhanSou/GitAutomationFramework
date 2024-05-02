package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage {
    @FindBy(id = "signin")
    public WebElement signInButton;
}
