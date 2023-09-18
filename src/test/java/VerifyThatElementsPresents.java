import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyThatElementsPresents {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);


    @Test
    public void verifyElementsPresents(){
        driver.get("https://jqueryui.com/");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Toggle")).click();

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class=\"demo-frame\"]")));
        WebElement toggleHeading = driver.findElement(By.xpath("//*[@id=\"effect\"]/h3"));

        Assert.assertTrue(toggleHeading.isDisplayed());
    }

    @Test
    public void verifyElementsIsEnable(){
        driver.get("https://jqueryui.com/");
        driver.manage().window().maximize();
        WebElement resizableLink = driver.findElement(By.linkText("Resizable"));

        System.out.println("Link is Enable :" + resizableLink.isEnabled());
    }
    @Test
    public void isSelected() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.linkText("Create new account")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name=\"sex\" and @value=\"1\"]")));
        WebElement radioButton = driver.findElement(By.xpath("//input[@name=\"sex\" and @value=\"1\"]"));
        System.out.println("Female radio button status : " + radioButton.isSelected());
        Assert.assertFalse(radioButton.isSelected());

        radioButton.click();
        Assert.assertTrue(radioButton.isSelected());
        System.out.println("Female radio button status : " + radioButton.isSelected());
    }

}
