import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

//    @Test  /// need to check with rossita
//    void scroll () throws IOException {
//        String[] sims = {"Sims games", "Legacy", "Spinoff"};
//
//        WebDriver driver = Helper.setupDriver();
//        driver.get(Scrolling.THESIMSURL);
//      //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Assert.assertEquals(driver.getCurrentUrl(), Scrolling.THESIMSURL);
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        driver.manage().window().maximize();
//
//        Scrolling scroll = new Scrolling(driver);
//        File screenshot = null;
//        File path = null;
//        for (int i = 0; i < sims.length; i++) {
//            jse.executeScript("arguments[0].scrollIntoView();", (scroll.elements));
//
//            screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//            path =  new File(Helper.FILEPATH +  (i + 1) + sims[i] + Helper.JPG);
//            FileUtils.copyFile(screenshot,path);
//            //navigation
//        }
//    }

    @Test // as per your request, copy text of para to file and screenshot of that same para
    void wiki() throws IOException {
        WebDriver driver = Helper.setupDriver();
        driver.get(W.WIKIJAVA);
        Assert.assertEquals(driver.getCurrentUrl(),W.WIKIJAVA);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        WebElement criticism = driver.findElement(By.id(W.CRITICISM));
        js.executeScript("arguments[0].scrollIntoView();",criticism);
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(Helper.FILEPATH+"Wiki paragraph Criticism"+Helper.JPG));

        String copyParagraph = driver.findElement(By.xpath(W.CRITICISMCOPYTEXT)).getText();
        Helper.createFile(Helper.FILEPATH,"Criticism paragraph"+Helper.TXT,copyParagraph);
        driver.quit();
    }

    @Test // windows, tabs, copy text and attribute
    void windowsAndTabs () throws InterruptedException {
        WebDriver driver = Helper.setupDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get(Windowstabs.BOOKURL);
        Assert.assertEquals(driver.getCurrentUrl(),Windowstabs.BOOKURL);
        driver.manage().window().maximize();
        String windowOne = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(Windowstabs.GOOGLEURL);
        String windowTwo = driver.getWindowHandle();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        driver.switchTo().window(windowOne);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.BOOKSEARCH))).sendKeys("thirteen");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.SEARCHBTN))).click();
        Thread.sleep(1000);
        String copyUrl2 =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.THIRTEENREASONS))).getAttribute("href");
        System.out.println(copyUrl2);
        driver.switchTo().newWindow(WindowType.TAB).get(copyUrl2);
        String secondBookTitle = driver.findElement(By.xpath(Windowstabs.SECONDBOOKTITLE)).getText();
        driver.switchTo().window(windowOne);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.CLICKONBOOK))).click();
        String firstBookTitle = driver.findElement(By.xpath(Windowstabs.BOOKFULLTILE)).getText();

        driver.switchTo().window(windowTwo);
        driver.manage().window().fullscreen();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.GOOGLESEARCHBOX))).sendKeys(firstBookTitle);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.GOOGLESEARCHBTN))).click();
        driver.navigate().back();
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.GOOGLESEARCHBOX))).sendKeys(secondBookTitle);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Windowstabs.GOOGLESEARCHBTN))).click();
        Thread.sleep(1000);

        driver.quit();
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
        driver.quit();

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
        driver.quit();
    }

}
