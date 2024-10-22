package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsPage {

    private Logger log = LoggerFactory.getLogger(ContactUsPage.class);

    public ContactUsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "id_contact")
    WebElement subjectHeading_select;
    @FindBy(css = "#email")
    WebElement email_input;
    @FindBy(css ="#id_order")
    WebElement order_reference;
    @FindBy(css ="#fileUpload" )
    WebElement attach_file_input;
    @FindBy(xpath = "//*[@id='message']")
    WebElement message;
    @FindBy(id ="submitMessage")
    WebElement send;

    public ContactUsPage select_object_from_subject_heading(String customer_service){
        Select select = new Select(subjectHeading_select);
        select.selectByIndex(1);
        select.selectByVisibleText(customer_service);
        log.info("Subject heading selected");
        return this;
    }

    public ContactUsPage send_email(String mail){
        email_input.clear();
        email_input.sendKeys(mail);
        log.info("email selected" + mail);
        return this;
    }

    public ContactUsPage send_order_reference(String number){
        order_reference.clear();
        order_reference.sendKeys(number);
        log.info("Order submitted properly" + number);
        return this;
    }

    public ContactUsPage file_upload(String path){
        attach_file_input.clear();
        attach_file_input.sendKeys(path);
        log.info("File attached");
        return this;
    }

    public ContactUsPage type_message(String text){
        message.clear();
        message.sendKeys(text);
        log.info("message submitted properly" + text);
        return this;
    }

    public ContactUsPage clickSubmitButton(){
        send.click();
        log.info("Button submitted properly");
        return this;
    }

}
