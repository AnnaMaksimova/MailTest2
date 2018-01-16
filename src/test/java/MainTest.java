import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MainTest {

    private Data data = new Data();
    WebDriver driver;

    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "c:\\Program Files\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);

        driver.get(data.loginPage);
        }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
        System.out.println("Browser was successfully quited!");
    }

    @Test
    public void EmailTest () throws InterruptedException {
        //Login to the mail box

        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.enterLogin(data.login);
        loginPage.enterPassword(data.password);

        //Assert, that the login is successful
        loginPage.assertLogin(data.to);

        //Create a new mail (fill address, subject and body fields)

        MailPage mailPage = PageFactory.initElements(driver, MailPage.class);
        mailPage.composeCN();
        mailPage.sendAddressTo(data.to);
        mailPage.sendTopic(data.topic);
        mailPage.sendContents(data.contents);

        //Save the mail as a draft.

        mailPage.saveMail();

        //Verify, that the mail presents in ‘Drafts’ folder.
        DraftsPage draftsPage = PageFactory.initElements(driver, DraftsPage.class);
        draftsPage.searchMail();
        draftsPage.assertDrafts(data.to);

        //Verify the draft content (addressee, subject and body).

        draftsPage.assertTopic(data.topic);

        draftsPage.assertContent(data.contents2);

        //Send the mail.

        draftsPage.sendFromDrafts();

        //Verify, that the mail was sent.
        draftsPage.sentMessage(data.sentMessage);

        //Verify, that the mail is in ‘Sent’ folder.

        SentPage sentPage = PageFactory.initElements(driver, SentPage.class);
        sentPage.assertSend(data.to);

        //Log out of your account
        LogOutPage logOutPage = PageFactory.initElements(driver, LogOutPage.class);
        logOutPage.findButton();



    }
}

