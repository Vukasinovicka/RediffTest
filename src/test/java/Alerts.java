import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Alerts {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Test
    public void handleAlerts(){
        driver.get("https://www.rediff.com/");

        driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/a[1]")).click();
        driver.findElement(By.id("login1")).sendKeys("v.ana.95@hotmal.com");
        driver.findElement(By.name("proceed")).click();

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
}
