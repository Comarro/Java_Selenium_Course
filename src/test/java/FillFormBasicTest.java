import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FillFormBasicTest {


    private static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

    @Test
    void shouldFillFirmWithSuccess() {
        //kod selenium//
        // 1. open url http://www.automationpractice.pl/index.php//
        driver = getDriver();
        log.info("Chrome Driver initialized properly");
        driver.get(APP_URL);
        log.info("URL opened " + APP_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.info("Timeout 5 sec");


        //chrome
        // WebDriverManager.chromedriver().setup();

        //firfox
        // WebDriverManager.firefoxdriver().setup();


        // 2 navigate to contact us//
        WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
        contactUs_link.click();
        log.info("contact us link clicked properly");


        // 3. Subject Heading//
        //WebElement SubjectHeading_select = driver.findElement(By.cssSelector("#id_contact"));
        //WebElement SubjectHeading_select = driver.findElement(By.xpath("#id_contact")); do poprawy nie właściwy xpatht
        WebElement SubjectHeading_select = driver.findElement(By.id("id_contact"));
        Select select = new Select(SubjectHeading_select);
        select.selectByIndex(1);
        log.info("Subject heading selected");

        // 4. Email address//
        WebElement email_input = driver.findElement(By.cssSelector("#email"));
        email_input.clear();
        email_input.sendKeys("testmail@ddd.com");
        log.info("email selected" + "testmail@ddd.com");


        // 5. Order reference//

        WebElement order_reference = driver.findElement(By.cssSelector("#id_order"));
        order_reference.clear();
        order_reference.sendKeys("123");
        log.info("Order submitted properly" + "123");
        // 6. Attach File//
        //6.
       // WebElement attach_file_input = driver.findElement(By.cssSelector("#fileUpload"));
        //attach_file_input.clear();attach_file_input.sendKeys("C:\\Users\\Desktop\\Testy.txt");
        //System.out.println("OK");
       // System.out.println("ok");
        // 7. Message
        WebElement message = driver.findElement(By.xpath("//*[@id='message']"));
        message.clear();
        message.sendKeys("I have a problem with my order.");
       log.info("message submited properly" + "I have a problem with my order.");

        //8.Submit button
        WebElement send = driver.findElement(By.id("submitMessage"));
        send.click();
        log.info("Buttopn submitet properly");
        //System.out.println("OK");
        //Asercja
         String expected_msg = "Hi, Your message has been successfully sent to our team.";
         log.info("expected_msg = " +expected_msg);
         WebElement success_msg = driver.findElement(By.cssSelector(".alert-success"));
         String actual_mgs = success_msg.getText();
         log.info("actual message =" +  actual_mgs);
         assertThat(expected_msg).contains(actual_mgs); //lużna asercja
         //assertThat(expected_msg).isEqualTo(actual_mgs); //precyzyjna asercja
        log.info("Assertion pass successfully");
        // 9. driver guit
        driver.quit();
        log.info("Driver closed properly");


    }

    private WebDriver getDriver() {
        String browserName = "chrome";
        switch (browserName) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*"); //pozwala na otwarcie ruchu sieciowego
                chromeOptions.addArguments("--disable-search-engine-screen"); //usówa popup od googla
                //chromeOptions.addArguments("--headLess"); //wszystko odbywa sie "w tle"
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
            }
            case "edge" -> {
            }
            default -> throw new UnsupportedOperationException("unsupported browser selected");
        }

        return driver;

    }

}





