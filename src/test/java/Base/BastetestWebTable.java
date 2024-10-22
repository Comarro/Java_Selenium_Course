package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BastetestWebTable {

    protected static WebDriver driver;
    private final String APP_URL = "https://cosmocode.io/automation-practice-webtable/";
    private Logger log = LoggerFactory.getLogger(BaseTest.class);

    private final String browserName = "chrome";

    Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10)) //max czekania
            .pollingEvery(Duration.ofSeconds(1)) //sprawedzanie czy jest rdy, co ile sekund
            .ignoring(NoSuchElementException.class);


    @BeforeEach
    void setup() {
        driver = getDriver();
        log.info("Chrome Driver initialized properly");
        driver.get(APP_URL);
        log.info("URL opened " + APP_URL);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //log.info("Timeout 5 sec");

    }

        @AfterEach
        void tearddown() {
            driver.quit();
            log.info("Driver closed properly");
        }


        private WebDriver getDriver(){

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

        protected void  clickCheckbox(WebElement element){           //fluent wait
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            log.info("Element " + element.getText() + " clicked");
        }

    protected WebElement findCheckbox(List <WebElement> List, int index){
        WebElement chceckBox = wait.until(ExpectedConditions.elementToBeClickable(List.get(index))); //fluent wait
        return chceckBox;
    }
    }


