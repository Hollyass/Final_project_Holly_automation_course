import org.apache.commons.io.FileUtils;
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

    // navigate, screenshots, scroll
    public static String THESIMSURL = "https://en.wikipedia.org/wiki/The_Sims";
    public static String FIRSTHEADING = "firstHeading"; //by id
    public static String SIMSGAMES = "Games";// by id
    public static String LIVINLARGE = "/html/body/div[3]/div[3]/div[5]/div[1]/div[8]/ul/li[1]/i/a"; //by full xpath
    public static String WIKIPEDIAMAIN = "/html/body/div[4]/div[2]/div/a"; // by full xpath
    public static String RANDOMPAGE = "/html/body/div[4]/div[2]/nav[1]/div/ul/li[4]/a"; // by full xpath

    // Test alerts
    public static String ALERATURL = "https://hollyass.github.io/alertsfortests/";
    public static String ALERTID = "btnAlert";
    public static String PROMPTID = "btnPrompt";
    public static String AFTERPROMPTTEXTID = "afterPrompt";





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

