import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage {

    @FindBy(id = "mailbox:login")
    WebElement loginField;

    @FindBy(id = "mailbox:password")
    WebElement passwordField;

    @FindBy(id = "mailbox:submit")
    WebElement submitButton;

    @FindBy(id = "PH_user-email")
    WebElement assertedLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterLogin(String s) {
        loginField.sendKeys(s);
    }

    public void enterPassword(String s) {
        passwordField.sendKeys(s);
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOf(assertedLogin));
    }

    public void assertLogin(String login) {
        Assert.assertTrue(assertedLogin.getText().contains("annaepam@mail.ru"), "Login is not successfull!");

    }

}