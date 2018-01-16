import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage extends BasePage {

    @FindBy(xpath = "//div[(contains(@style, 'width'))]//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad'][text()='Написать письмо']")
    WebElement writeButton;

    @FindBy(xpath = "//textarea[@class='js-input compose__labels__input'][@data-original-name='To']")
    WebElement fieldTo;

    @FindBy(xpath = "//input[@name='Subject']")
    WebElement fieldTopic;

    @FindBy(id = "tinymce")
    WebElement fieldContent;

    @FindBy(xpath = "//span[@class='b-toolbar__btn__text'][text()='Сохранить']")
    WebElement saveButton;


    public MailPage(WebDriver driver) {
        super(driver);
    }

    public void composeCN() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOf(writeButton)).click();
    }

    public void sendAddressTo(String sendWhat) {
        fieldTo.sendKeys(sendWhat);
    }

    public void sendTopic(String sendWhat) {
        fieldTopic.sendKeys(sendWhat);
    }

    public void sendContents(String sendWhat) {
        String frameName = driver.findElement(By.xpath("//textarea[contains(@id, 'toolkit-')]")).getAttribute("id");
        driver.switchTo().frame(frameName + "_ifr");
        fieldContent.sendKeys(sendWhat);
        driver.switchTo().parentFrame();
    }

    public void saveMail() {
        saveButton.click();
    }


}

