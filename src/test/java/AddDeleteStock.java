import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddDeleteStock {


    public static final WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver,10);


    @Test
    public void addStock() {
        driver.get("https:/www.rediff.com/");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("a.moneyicon.relative")).click();

        driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[1]")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"useremail\"]"))));
        driver.findElement(By.xpath("//*[@id=\"useremail\"]")).sendKeys("v.ana.95@hotmail.com");
        driver.findElement(By.xpath("//*[@id=\"userpass\"]")).sendKeys("Vaki@1504");
        driver.findElement(By.id("rememberflag")).click();
        driver.findElement((By.xpath("//*[@id=\"userpass\"]"))).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("portfolioid")));
        driver.findElement(By.id("portfolioid"));
        Select select = new Select(driver.findElement(By.id("portfolioid")));
        select.selectByVisibleText("Test1");

        driver.findElement(By.id("addStock")).click();
        driver.findElement(By.name("addstockname")).sendKeys("Nestle India");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\"ajax_listOfOptions\"]/div[1]")));
        driver.findElement(By.xpath("//div[@id=\"ajax_listOfOptions\"]/div[1]")).click();
        //1st Method to enter Date
        //driver.findElement(By.id("stockAddDate")).sendKeys("17-09-2023");

        driver.findElement(By.id("stockPurchaseDate")).click();
        selectDataIncalendar("13/06/2017");

        driver.findElement(By.id("addstockqty")).sendKeys("125");
        driver.findElement(By.id("addstockprice")).sendKeys("500");
        driver.findElement(By.id("addStockButton")).click();

        waitForLoad();

        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id=\"stock\"]/tbody/tr[2]/td[2]/span")));
       // String stockName = driver.findElement(By.xpath("//table[@id=\"stock\"]/tbody/tr[2]/td[2]/span")).getText();
      //  Assert.assertEquals("Nestle India", stockName);
       int stockRowNum = getStockRowNumber("Nestle India");

       System.out.println("Row number is: " + stockRowNum);
       if (stockRowNum==-1) {
           Assert.fail("Stock not Found");
       }
    }
    @Test
    public void deleteStock(){

        int stockRowNum = getStockRowNumber("Nestle India");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id=\"stock\"]//tbody/tr["+stockRowNum+"]/td[1]")));
        driver.findElement(By.xpath("//table[@id=\"stock\"]//tbody/tr["+stockRowNum+"]/td[1]")).click();

        driver.findElements(By.xpath("//input[@name=\"Delete\"]")).get(stockRowNum-1).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        waitForLoad();

        int stockRowNumAfterDelete = getStockRowNumber("Nestle India");
        Assert.assertEquals(-1,stockRowNumAfterDelete);

    }

    public int getStockRowNumber(String StockName) {


        List<WebElement> totalRows = driver.findElements(By.xpath("//table[@id=\"stock\"]//tbody/tr"));

        int rowNum = 0;
        for (WebElement row : totalRows) {
            List<WebElement> rowCells = row.findElements(By.tagName("td"));
            rowNum++;

            for (WebElement cell : rowCells) {
                String cellDate = cell.getText();
                System.out.println(cellDate);

                if (!cellDate.isEmpty() && cellDate.contains(StockName)) {
                    return rowNum;
                }


            }
        }
        return -1;
    }

    public void waitForLoad() {
        int i = 0;
        while (i != 20) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String pageState = (String) js.executeScript("return document.readyState");

            if (pageState.equals("complete")) {
                break;
            }else {
                waitLoad(1);
            }
        }
        waitLoad(2);

        i = 0;

        while (i !=180) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean jsResult = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active ==0;");

            if (jsResult) {
                break;
            } else {
                waitLoad(1);
            }
        }

    }

    public void waitLoad( int i ) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //throw new RuntimeException(e);
    public void selectDataIncalendar(String date) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date expectedDate =  dateFormat.parse(date);

            String day = new SimpleDateFormat("dd").format(expectedDate);
            String month = new SimpleDateFormat("MMMM").format(expectedDate);
            String year = new SimpleDateFormat("yyyy").format(expectedDate);

            String expectedMonthYear = month + " " + year;


            while (true) {
                String displayData = driver.findElement(By.className("dpTitleText")).getText();

                if (expectedMonthYear.equals(displayData)) {

                    driver.findElement(By.xpath("//td[text()= '"+day+"']")).click();
                    break;
                } else if (expectedDate.compareTo(currentDate) >0) {
                    driver.findElement(By.xpath("//*[@id=\"datepicker\"]/table/tbody/tr[1]/td[4]/button")).click();
                }else {
                    driver.findElement(By.xpath("//*[@id=\"datepicker\"]/table/tbody/tr[1]/td[2]/button")).click();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


