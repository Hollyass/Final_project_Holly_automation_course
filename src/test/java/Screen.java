import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Screen {

    public static final String CATFLOWER = "http://www.1stinflowers.com/articles/poisonous-plants-for-cats.html";
    public static final String CATRASH = "poipoison-cat-rash"; // id
    public static final String CATVOMIT = "poipoison-cat-upset-stomach"; // id
    public static final String CATORGAN = "poipoison-cat-organ-failure"; // id
    public static final String EMORGENCYCONTACT = "dpoiemer"; // id

    WebElement catRash;
    WebElement catVomit;
    WebElement catOrgan;

    WebElement[] catScroll = new WebElement[3];

    public Screen(WebDriver driver) {
        catScroll[0] = driver.findElement(By.id(CATRASH));
        catScroll[1]= driver.findElement(By.id(CATVOMIT));
        catScroll[2] = driver.findElement(By.id(CATORGAN));
    }

}
