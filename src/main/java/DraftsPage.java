import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DraftsPage extends BasePage {

    // private WebDriver driver;

    @FindBy(xpath = "//span[@class=\"b-nav__item__text\"][text()='Черновики']")
    WebElement draftsButton;

    @FindBy(xpath = "//div[@class='b-datalist__item__addr'][text()='annaepam@mail.ru'][1]")
    WebElement searchMail;

    @FindBy(xpath = "//div[@title='Отправить (Ctrl+Enter)']")
    WebElement sendButton;

    @FindBy(name = "Subject")
    WebElement draftsTopic;

    @FindBy(xpath = "//div[contains(@id, 'style_')]/div")
    WebElement draftsContent;

    @FindBy(xpath = "//div[@data-mnemo='loadProgress' and text()='Идёт сохранение']")
    WebElement saving;

    @FindBy(xpath = "//div[@class='message-sent__title']")
    WebElement message;

    public DraftsPage(WebDriver driver) {
        super(driver);
    }


    public void searchMail() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@data-mnemo='loadProgress' and text()='Идёт сохранение']")));
        draftsButton.click();
    }

    public void assertDrafts(String assertTo) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(searchMail));
        Assert.assertTrue(searchMail.getText().equals(assertTo),"The mail is not in Draft-folder");
    }

    public void assertTopic(String assertWhat) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(searchMail)).click();
        Assert.assertTrue(draftsTopic.getAttribute("value").equals(assertWhat),"Topic is incorrect!");

    }

    public void assertContent(String assertWhat) throws InterruptedException {
        String frameName2 = driver.findElement(By.xpath("//textarea[contains(@id, 'toolkit-')]")).getAttribute("id");
        driver.switchTo().frame(frameName2 + "_ifr");
        Assert.assertTrue(draftsContent.getText().equals(assertWhat), "Content is incorrect!");
        driver.switchTo().parentFrame();
    }

    public void sendFromDrafts() {
        sendButton.click();

    }

    public void sentMessage(String sentMessage) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(message));
        Assert.assertTrue(message.getText().contains(sentMessage), "The mail was not sent!");
        wait.ignoring(StaleElementReferenceException.class, WebDriverException.class).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class=\"b-nav__item__text\"][text()='Черновики']")));
        draftsButton.click();
    }

}
