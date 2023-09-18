import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindBrokenLink {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);

    @Test
    public void Test(){
        driver.get("https://edition.cnn.com/");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links are: " + links.size());

          for (WebElement link : links) {
              String URL = link.getAttribute("href");
              GetlinkStatus.verifyLink(URL);
          }
          System.out.println("Total Number Of Broken Links");
          GetlinkStatus.getInvalidLink();

    }
}
