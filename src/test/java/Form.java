import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Form {

    private final String USERNAMEFIELD = "#HTMLFormElements > table > tbody > tr:nth-child(1) > td > input[type=text]"; // BY CSS
    private final String PASSWORDFIELD = "#HTMLFormElements > table > tbody > tr:nth-child(2) > td > input[type=password]"; // BY CSS
    public static final String COMMENTFIELD = "#HTMLFormElements > table > tbody > tr:nth-child(3) > td > textarea"; // BY CSS
   //private final String FILENAME = "#HTMLFormElements > table > tbody > tr:nth-child(1) > td > input[type=text]"; // BY CSS
    private final String CHECKBOX1 = "/html/body/div/div[3]/form/table/tbody/tr[5]/td/input[1]"; // BY fullxapth
    private final String CHECKBOX2 = "/html/body/div/div[3]/form/table/tbody/tr[5]/td/input[2]"; // BY fullxapth
    private final String CHECKBOX3 = "/html/body/div/div[3]/form/table/tbody/tr[5]/td/input[3]"; // fullxapth
    private final String RADIOBOX1 = "/html/body/div/div[3]/form/table/tbody/tr[6]/td/input[1]"; // BY full path
    private final String RADIOBOX2 = "/html/body/div/div[3]/form/table/tbody/tr[6]/td/input[2]"; // BY full path
    private final String RADIOBOX3 = "/html/body/div/div[3]/form/table/tbody/tr[6]/td/input[3]"; // BY full path
    public static final String MULTIPLESELCETFIRST = "/html/body/div/div[3]/form/table/tbody/tr[7]/td/select/option[1]"; // BY full xpath
    public static final String MULTIPLESELCETLAST = "/html/body/div/div[3]/form/table/tbody/tr[7]/td/select/option[4]"; // BY full xpath
    private final String DROPDOWN = "/html/body/div/div[3]/form/table/tbody/tr[8]/td/select"; // by full
    public static final String SUBMITBTN = "/html/body/div/div[3]/form/table/tbody/tr[9]/td/input[2]"; // by fullxpath
    public static final String TEXTVLIADATION = "/html/body/div/div[3]";

    WebElement userNameField;
    WebElement passwordField;
    WebElement commentField;
    WebElement checkbox1field;
    WebElement checkbox2field;
    WebElement checkbox3field;
    WebElement radioBox1field;
    WebElement radioBox2field;
    WebElement radioBox3field;
    Select dropDown;

    public Form(WebDriver driver) {
        this.userNameField = driver.findElement(By.cssSelector(USERNAMEFIELD));;
        this.passwordField = driver.findElement(By.cssSelector(PASSWORDFIELD));
        this.commentField = driver.findElement(By.cssSelector(COMMENTFIELD));
        commentField.clear();
        this.checkbox1field = driver.findElement(By.xpath(CHECKBOX1));
        this.checkbox2field = driver.findElement(By.xpath(CHECKBOX2));
        this.checkbox3field = driver.findElement(By.xpath(CHECKBOX3));
        this.radioBox1field = driver.findElement(By.xpath(RADIOBOX1));
        this.radioBox2field = driver.findElement(By.xpath(RADIOBOX2));
        this.radioBox3field = driver.findElement(By.xpath(RADIOBOX3));
        this.dropDown = new Select(driver.findElement(By.xpath(DROPDOWN)));
    }

    public void signingUp (User user) {
        userNameField.sendKeys(user.username);
        passwordField.sendKeys(user.password);
        commentField.sendKeys(user.comment);
        if (user.checkbox1) {
            checkbox1field.click();
        }
        if (user.checkbox2) {
            checkbox1field.click();
        }
        if (user.checkbox3) {
            checkbox1field.click();
        }
        if (user.radiobox1){
            radioBox1field.click();
        }
        if (user.radiobox2){
            radioBox2field.click();
        }
        if (user.radiobox3){
            radioBox3field.click();
        }
        dropDown.selectByIndex(user.dropdown);
    }
}

