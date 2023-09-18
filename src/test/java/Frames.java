import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Frames {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);

    @Before
    public void openBrowser(){
        driver.get("https://jqueryui.com/");
        driver.manage().window().maximize();
    }



    @Test
    public void getFrames() {
        driver.findElement(By.linkText("Button")).click();
        driver.findElements(By.className("entry-title"));
        Assert.assertEquals("Button",driver.findElement(By.className("entry-title")).getText());



        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class=\"demo-frame\"]")));
        WebElement firstButton = driver.findElement(By.xpath("//*[@class=\"widget\"]/button"));
        Assert.assertEquals("A button element", firstButton.getText());

        driver.switchTo().parentFrame();

        WebElement defaultFunkcionaliti = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/ul/li[1]/a"));
        Assert.assertEquals("Default functionality", defaultFunkcionaliti.getText());
    }
}
