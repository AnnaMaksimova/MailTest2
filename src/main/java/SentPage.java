import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SentPage extends BasePage {

    @FindBy(xpath = "//span[@class=\'b-nav__item__text\'][text()=\'Отправленные\']")
    WebElement sentMail;

    @FindBy(xpath = "//div[@class='b-datalist__item__addr'][text()='annaepam@mail.ru'][1]")
    WebElement seachMail;

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public void assertSend(String assertWhat) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOf(sentMail)).click();
        Assert.assertTrue(seachMail.getText().equals(assertWhat), "Letter is not found!");

    }
}
