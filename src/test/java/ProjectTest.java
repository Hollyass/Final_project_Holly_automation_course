import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProjectTest {

    @Test
    void navigate (){
        WebDriver driver = Helper.setupDriver();
        driver.get("");

     //   File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screenshot,new File(Helper.FILEPATH +"Alert shown"+Helper.JPG));
    }

    @Test
    void alerts() throws IOException {
        WebDriver driver = Helper.setupDriver();
        driver.get(Helper.ALERATURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.ALERTID))).click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Helper.PROMPTID))).click();
        wait.until(ExpectedConditions.alertIsPresent()).sendKeys("Debbie");
        driver.switchTo().alert().accept();

        String acceptedText = driver.findElement(By.id(Helper.AFTERPROMPTTEXT)).getText();
        Helper.createFile(Helper.FILEPATH,"Prompt alert message"+Helper.TXT,acceptedText);

    }
}
