package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private Logger log = LoggerFactory.getLogger(BaseTest.class);

    private final String browserName = "chrome";

    protected String expected_msg = "Your message has been successfully sent to our team.";



    @BeforeEach
    void setup() {
        driver = getDriver();
        log.info("Chrome Driver initialized properly");
        driver.get(APP_URL);
        log.info("URL opened " + APP_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.info("Timeout 5 sec");

    }

    @AfterEach
    void tearddown() {
        driver.quit();
        log.info("Driver closed properly");
    }

    private WebDriver getDriver() {

        switch (this.browserName) {
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
