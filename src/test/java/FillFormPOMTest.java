import Base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ContactUsPage;
import pages.ContactUsVerificationPage;
import pages.HomePage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FillFormPOMTest extends BaseTest {


    private String path = "C:\\Users\\Public\\Documents\\test.txt";
    private String number = "123";
    private String mail= "testmail@ddd.com";
    private String customer_service= "Customer service";
    private String text= "I have a problem with my order.";




    @Test
    void shouldSendFormWithPOM(){
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        ContactUsVerificationPage contactUsVerificationPage = new ContactUsVerificationPage(driver);
        homePage.contactUsPageClick();
        //jedna opcja//
        contactUsPage.select_object_from_subject_heading(customer_service);
        contactUsPage.send_email(mail);
        contactUsPage.send_order_reference(number);
        //2 opcja
        //contactUsPage.select_object_from_subject_heading("Customer service").send_email("testmail@ddd.com").send_order_reference("123");
        contactUsPage.file_upload(path);
        contactUsPage.type_message(text);
        contactUsPage.clickSubmitButton();
        String check_alert_message = contactUsVerificationPage.check_alert_success();
        assertThat(expected_msg).withFailMessage("expected sa rózne").isEqualTo(check_alert_message);

    }
}
