import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookImagesPiped {

  private static final long WAIT_PERIOD_LONG = 6000L;

  public static void main(String[] args) throws URISyntaxException,
      JSONException, IOException, InterruptedException {
    System.err.println("[DEBUG] Headless.main() - args = " + args.length);

    if (args.length == 0) {
      System.err.println(
          "FacebookImagesPiped.main(): specify the facebook password as an argument");
      System.exit(-1);

    }
    WebDriverManager.chromedriver().setup();
    ChromeOptions co = new ChromeOptions();
    co.addArguments("--disable-notifications");
    WebDriver driver = new ChromeDriver(co);
    {
      String baseUrl2 = "https://www.facebook.com";

      // launch Fire fox and direct it to the Base URL
      driver.get(baseUrl2);

      new Actions(driver).sendKeys(driver
          .findElements(By.xpath("//input[@id=\"email\"]")).get(0),
          "ss533@cornell.edu").perform();
      new Actions(driver).sendKeys(driver
          .findElements(By.xpath("//input[@id=\"pass\"]")).get(0),
          args[0]).perform();
      driver.findElements(By.xpath("//button[@type=\"submit\"]"))
          .get(0).click();

      Thread.sleep(WAIT_PERIOD_LONG);
//      String baseUrl = "https://www.facebook.com/megha.panchamukhi.7/photos_by";
//      String baseUrl = "https://www.facebook.com/poornima.kulkarni.357/photos_of";
//      String baseUrl = "https://www.facebook.com/profile.php?id=100002218014948&sk=photos_of";
//      String baseUrl = "https://www.facebook.com/profile.php?id=100002218014948&sk=photos_by";
//      String baseUrl = "https://www.facebook.com/varsha.rohidekar.9/photos";
//          "https://www.facebook.com/media/set/?set=a.192604700778537&type=3",
//          "https://www.facebook.com/media/set/?set=a.161396873899320&type=3",
//          "https://www.facebook.com/media/set/?set=a.161395033899504&type=3",
//          "https://www.facebook.com/media/set/?set=a.161145017257839&type=3",
//          "https://www.facebook.com/media/set/?set=a.160399013999106&type=3",
//          "https://www.facebook.com/media/set/?set=a.160601317312209&type=3",
//          "https://www.facebook.com/media/set/?set=a.151229914916016&type=3",
//          "https://www.facebook.com/media/set/?set=a.145295148842826&type=3"

      ////
      //// Each album url must be given separately. Anything else is not worth the
      //// loss of control
      ////
      List<String> subList ;
      if (args.length > 1) {
        subList = Arrays.asList(args).subList(2, args.length);
        
      }else {
        subList = new ArrayList<>();
        subList.add("https://www.facebook.com/sindhu.gombi/photos_by");
      }
      for (String facebookAlbumUrl : subList) {
//          "https://www.facebook.com/varsha.rohidekar.9/photos_by"
//          "https://www.facebook.com/sindhu.gombi/photos_by",
//          "https://www.facebook.com/sindhu.gombi/photos_of"
//          "https://www.facebook.com/media/set/?set=a.103236316503075&type=3"
//          "https://www.facebook.com/media/set/?set=a.103236316503075&type=3"
          // "https://www.facebook.com/media/set/?set=a.418968131596557&type=3"
          // "https://www.facebook.com/media/set/?set=a.103900176436689&type=3"
//          "https://www.facebook.com/media/set/?set=a.220906377933744&type=3",
//          "https://www.facebook.com/media/set/?set=a.289379861086395&type=3",
//          "https://www.facebook.com/media/set/?set=a.153583031332746&type=3",
//          "https://www.facebook.com/media/set/?set=a.549330278424684&type=3",
//          "https://www.facebook.com/media/set/?set=a.589142874443424&type=3",
//          "https://www.facebook.com/media/set/?set=a.558416390849406&type=3",
//          "https://www.facebook.com/media/set/?set=a.294639280560453&type=3",
//          "https://www.facebook.com/media/set/?set=a.278720775485637&type=3",
//          "https://www.facebook.com/media/set/?set=a.275431262481255&type=3",
//          "https://www.facebook.com/media/set/?set=a.100979533259763&type=3"
//      }) {
        System.err.println("FacebookImages.main() - next album: "
            + facebookAlbumUrl);

        driver.get(facebookAlbumUrl);
        Thread.sleep(WAIT_PERIOD_LONG);
        {
          List<WebElement> aHrefElements = driver
              .findElements(By.xpath("//a[@href]"));

          for (WebElement elem : aHrefElements) {
            System.err.println(
                "[DEBUG] Headless.main() all photos in album: "
                    + elem.getAttribute("href"));
            System.out.println(elem.getAttribute("href"));

          }
        }
      }
      driver.close();
    }

  }
}
