import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Helper {
    //file paths
    public static String FILEPATH = "C:\\Users\\HollyShamraevsky\\Desktop\\FinalProject\\";
    public static String JPG = ".jpg";
    public static String TXT = ".txt";


    // Test alerts
    public static String ALERATURL = "https://hollyass.github.io/alertsfortests/";
    public static String ALERTID = "btnAlert";
    public static String PROMPTID = "btnPrompt";
    public static String AFTERPROMPTTEXT = "afterPrompt";



    public static ChromeDriver setupDriver (){
        System.setProperty("webdriver.chrome.driver","res\\chromedriver.exe");
        return new ChromeDriver();
    }

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

