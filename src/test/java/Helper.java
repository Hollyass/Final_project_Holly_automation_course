import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {
    //file paths
    public static final String FILEPATH = "C:\\Users\\HollyShamraevsky\\Desktop\\FinalProject\\";
    public static final String JPG = ".jpg";
    public static final String TXT = ".txt";


    // Test alerts
    public static final String ALERATURL = "https://hollyass.github.io/alertsfortests/";
    public static final String ALERTID = "btnAlert";
    public static final String PROMPTID = "btnPrompt";
    public static final String AFTERPROMPTTEXTID = "afterPrompt";


    // form
    public static final String FORM = "https://testpages.herokuapp.com/styled/basic-html-form-test.html";
    public static User userOne = new User("alice",
            "aliceforever",
            "This is a test, whats up doc",
            false,
            false,
            true,
            false,
            true,
            false,
            3);

    //driver
    public static ChromeDriver setupDriver (){
        System.setProperty("webdriver.chrome.driver","res\\chromedriver.exe");
        return new ChromeDriver();
    }

    //create file with text
    public static void createFile(String pathname,String filename, String text) throws IOException {
        File file = new File(pathname + filename);
        if (file.createNewFile()){
            System.out.println("File was created successfully");
        }else {
            System.out.println("File was previously created");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(text);
        writer.close();
    }

}

