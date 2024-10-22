package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private Logger log = LoggerFactory.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#contact-link a")
    WebElement contactUs_link;  //te 2to jest to samo co w 16

    //WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
       public HomePage contactUsPageClick(){
        contactUs_link.click();
        log.info("contact us link clicked properly");
        return this;
       }
}
