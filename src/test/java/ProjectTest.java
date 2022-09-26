import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.TimeUnit;

public class ProjectTest {

    @Test
    void scroll () throws IOException {
        String[] sims = {"Sims games", "Legacy", "Spinoff"};

        WebDriver driver = Helper.setupDriver();
        driver.get(Scrolling.THESIMSURL);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertEquals(driver.getCurrentUrl(), Scrolling.THESIMSURL);
        driver.manage().window().maximize();
        File screenshot = null;
        File path = null;
        Scrolling scroll = new Scrolling(driver);
        for (int i = 0; i < sims.length; i++) {
            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path =  new File(Helper.FILEPATH +  (i + 1) + sims[i] + Helper.JPG);
            FileUtils.copyFile(screenshot,path);
            //navigation
                jse.executeScript("arguments[0].scrollIntoView();",scroll.elements);
        }
    }

    @Test
    void windowsTabs (){
        WebDriver driver = Helper.setupDriver();
        driver.get("");
    }
    @Test // navigation and assert
    void navigateAssert () throws InterruptedException {
        WebDriver driver = Helper.setupDriver();
        driver.get(Navigate.MAINWIKIURL);
        Assert.assertEquals(driver.getCurrentUrl(), Navigate.MAINWIKIURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Navigate.RANDOMPAGE))).click();
        String pageUrl = driver.getCurrentUrl();
        Thread.sleep(1000);
        driver.navigate().refresh();
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),Navigate.MAINWIKIURL);
        driver.navigate().forward();
        Assert.assertEquals(driver.getCurrentUrl(),pageUrl); // checking that back and forward got me to the same url
        Thread.sleep(1000);
        driver.navigate().back();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Navigate.RANDOMPAGE))).click();
        Assert.assertNotSame(driver.getCurrentUrl(),pageUrl); // checking that clicking on random again is not same
        Thread.sleep(1000);


    }
    @Test // alerts+prompts, create file, save text in file
    void alertsAndText () throws IOException {
        WebDriver driver = Helper.setupDriver();
        driver.get(Helper.ALERATURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.ALERTID))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.PROMPTID))).click();
        wait.until(ExpectedConditions.alertIsPresent()).sendKeys("Debbie");
        driver.switchTo().alert().accept();

        String acceptedText = driver.findElement(By.id(Helper.AFTERPROMPTTEXTID)).getText();
        Helper.createFile(Helper.FILEPATH,"Prompt alert message"+Helper.TXT,acceptedText);
    }

    @Test
    void screenShot() throws IOException {
        String[] sims = {"The sims wiki", "Sims games", "Living large", "random page"};
        WebDriver driver = Helper.setupDriver();
        driver.get(Scrolling.THESIMSURL);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        File screenshot = null;
        File path = null;

        for (int i = 0; i < sims.length; i++) {
            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path =  new File(Helper.FILEPATH +  (i + 1) + sims[i] + Helper.JPG);
            FileUtils.copyFile(screenshot,path);
            //navigation

        }

        screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }


}
