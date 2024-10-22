package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsVerificationPage {

    private Logger log = LoggerFactory.getLogger(ContactUsPage.class);

    public ContactUsVerificationPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".alert-success")
    WebElement success_msg;

    public String check_alert_success(){
        String actual_msg = success_msg.getText();
        log.info("Alert success is : " + actual_msg);
        return actual_msg;
    }
}
