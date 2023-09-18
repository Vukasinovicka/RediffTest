import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DropDown {

    WebDriver driver = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Test
    public void selectedDropDown(){

        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Create new account")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("month")));
        WebElement monthDropDown = driver.findElement(By.id("month"));
        //Selected ByIndex
        Select select = new Select(monthDropDown);
        select.selectByIndex(5);

        //Select ByValue
        select.selectByValue("11");


        //Select ByVisibleText
        select.selectByVisibleText("Aug");

        //Select getOptions
        List<WebElement> monthValues = select.getOptions();
        for (WebElement month : monthValues) {
            System.out.println(month.getText());
        }
    }

    @Test
    public void selectMultiDropDown (){
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        driver.manage().window().maximize();

        driver.switchTo().frame("iframeResult");
        WebElement multiSelect = driver.findElement(By.id("cars"));

        Select select = new Select(multiSelect);

        System.out.println(select.isMultiple());

        select.selectByIndex(1);
        select.selectByIndex(3);

        System.out.println(select.getFirstSelectedOption().getText());

        select.deselectAll();


    }
}

