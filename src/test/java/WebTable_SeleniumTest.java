import Base.BaseTest;
import Base.BastetestWebTable;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTable_SeleniumTest extends BastetestWebTable {

    private Logger log = LoggerFactory.getLogger(WebTable_SeleniumTest.class);

    @Test
    @DisplayName("Fajny test na table")
    void shouldCheckCapitalInWebTable() throws InterruptedException {
        String SearchCountry = "Portugal";
        String expected_capitol = "Lisbon";
        String capitolFromCountry = getCapitolFromCountry(SearchCountry);

        assertThat(capitolFromCountry).isEqualTo(expected_capitol);

    }

    private String getCapitolFromCountry(String Country) throws InterruptedException {
        List<WebElement> rows = driver.findElements(By.cssSelector("#countries tbody tr"));
        int size = rows.size();
        log.info("Wielkość tablicy: " + size);

        int rowCount = 0;
        for (WebElement row : rows) {
            if (row.getText().contains(Country)){
                break;
            }
            rowCount ++;
        }


        int rowIndex = rowCount+1;
        log.info("My row = " + (rowCount + 1));  // lub + rowIndex
        ScrollToWebElement(rows.get(rowIndex+5));
        WebElement CapitalOfCountry = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + rowIndex + ") td:nth-child(3)"));
        log.info("Capital of " + Country + " is " + CapitalOfCountry.getText());
        //ScrollToWebElement(CapitalOfCountry);
        //Thread.sleep(5000); //nie używac w tescie bo zajmuje niepotrzebnie czas - tylko przy tworzeniu testu ewnetualnie
        return CapitalOfCountry.getText();
    }

    @Test
    @DisplayName("drugi fajniejszy test")
    void ShouldSelectCheckbox() throws InterruptedException {
        //select checkbox for Poland
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".hasVisited[type='checkbox']"));
        WebElement checkbox = findCheckbox(checkboxes, 139);
        ScrollToWebElement(checkboxes.get(145));
        clickCheckbox(checkbox);
        assertThat(checkbox.isSelected()).isTrue();
        //Thread.sleep(5000); //Do usuniecia, nie robi sie takich w testach


    }

    private void ScrollToWebElement (WebElement element){
       new Actions(driver).scrollToElement(element).perform();
    }
}
