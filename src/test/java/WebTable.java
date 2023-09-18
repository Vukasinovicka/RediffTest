import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebTable {



        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,10);

        @Test
        public void manageWebTable() {
            driver.get("https:/www.rediff.com/");
            driver.manage().window().maximize();
            driver.findElement(By.cssSelector("a.moneyicon.relative")).click();
            driver.findElement(By.linkText("Indices")).click();
            driver.findElement(By.id("showMoreLess")).click();

           List totalRow = driver.findElements(By.xpath("//*[@class=\"dataTable\"]/tbody/tr"));
           System.out.println("Total number of rows : " + totalRow.size() );

           List totalColumns = driver.findElements(By.xpath("//*[@class=\"dataTable\"]/tbody/tr/td"));
           System.out.println("Total number of columns : " + totalColumns.size());

        }
    }

