package Pages;

import Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {

    public HistoryPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "historyId")
    public WebElement historyTab;
}
