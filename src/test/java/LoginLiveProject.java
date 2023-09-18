import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginLiveProject {

    WebDriver driver = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver,10);

    @Test
    public void login(){
        driver.get("https:/www.rediff.com/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("a.moneyicon.relative")).click();
        driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[1]")).click();
        wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//*[@id=\"useremail\"]"))));
        driver.findElement(By.xpath("//*[@id=\"useremail\"]")).sendKeys("v.ana.95@hotmail.com");
        driver.findElement(By.xpath("//*[@id=\"userpass\"]")).sendKeys("Vaki@1504");
        driver.findElement(By.id("rememberflag")).click();
        driver.findElement((By.xpath("//*[@id=\"userpass\"]"))).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"username\"]/a")));
        driver.findElement(By.xpath("//*[@id=\"username\"]/a")).isDisplayed();


    }
}


