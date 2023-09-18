import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtractTextInSelenium {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);


    @Test
    public void testimplicitWait(){
        driver.get("https://www.facebook.com/r.php?locale=en_US");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[1]")));

       String headingText =  driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[1]/div[1]")).getText();
       System.out.println(headingText);
       Assert.assertEquals("Create a new account", headingText);

       String logintextButton = driver.findElement(By.name("websubmit")).getText();
       System.out.println(logintextButton);
       Assert.assertEquals("Sign Up", logintextButton);

       String placeHolder = driver.findElement(By.xpath("//*[@name=\"reg_email__\"]")).getAttribute("aria-label");
       System.out.println(placeHolder);
       Assert.assertEquals("Mobile number or email", placeHolder);

       driver.quit();
    }



}
