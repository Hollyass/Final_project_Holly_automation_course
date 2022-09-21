import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    void navigateAndScreenshotAndScroll () throws IOException, InterruptedException {
        WebDriver driver = Helper.setupDriver();
        driver.get(Helper.THESIMSURL);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.FIRSTHEADING)));
        driver.manage().window().maximize();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(Helper.FILEPATH + "1 - the sims wiki" + Helper.JPG));
        WebElement simsGames = driver.findElement(By.id(Helper.SIMSGAMES));
        jse.executeScript("arguments[0].scrollIntoView();",simsGames);
        File screenshot2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot2, new File(Helper.FILEPATH + "2 - sims games" + Helper.JPG));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Helper.LIVINLARGE))).click();
        File screenshot3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot3, new File(Helper.FILEPATH + "3 - livin large" + Helper.JPG));
        jse.executeScript("window.scrollBy(0,-(document.body.scrollHeight))");
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().forward();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Helper.WIKIPEDIAMAIN))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Helper.RANDOMPAGE))).click();
        File random = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(random, new File(Helper.FILEPATH + "4 - random page" + Helper.JPG));
        driver.navigate().back();
        driver.navigate().forward();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.FIRSTHEADING)));
    //    File random2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       // FileUtils.copyFile(random2, new File(Helper.FILEPATH + "5 - another random page" + Helper.JPG));



    }

    @Test
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
}
