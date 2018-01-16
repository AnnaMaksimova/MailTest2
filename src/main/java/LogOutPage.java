import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage extends BasePage {

    @FindBy(id = "PH_logoutLink")
    WebElement button;

    public LogOutPage(WebDriver driver) {
        super(driver);
    }

       public void findButton() {
        button.click();
    }
}
