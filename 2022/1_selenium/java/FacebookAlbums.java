import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Use lambdas instead of Unix pipes if possible, it's easier to cold tweak
 */
@Deprecated // Parallelism makes the driver reference stale elements. Having standalone
            // windows requires separate logins. This could raise security issues for
            // Facebook.
public class FacebookAlbums {

  private static final long WAIT_PERIOD_LONG = 6000L;

  // TODO: use stdin loop
  public static void main(String[] args) throws URISyntaxException,
      JSONException, IOException, InterruptedException {
    System.err
        .println("[DEBUG] Headless.main() - args = " + args.length);

    if (args.length == 0) {
      System.err.println(
          "FacebookImagesPiped.main(): specify the facebook password as an argument");
      System.exit(-1);

    }
    WebDriverManager.chromedriver().setup();
    ChromeOptions co = new ChromeOptions();
    co.addArguments("--disable-notifications");
    WebDriver driver2 = new ChromeDriver(co);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        System.out.println("Shutdown Hook is running");
        driver2.close();
      }
    });

    {
      String baseUrl2 = "https://www.facebook.com";

      // launch Fire fox and direct it to the Base URL
      driver2.get(baseUrl2);

      new Actions(driver2).sendKeys(driver2
          .findElements(By.xpath("//input[@id=\"email\"]")).get(0),
          "ss533@cornell.edu").perform();
      new Actions(driver2).sendKeys(driver2
          .findElements(By.xpath("//input[@id=\"pass\"]")).get(0),
          args[0]).perform();
      driver2.findElements(By.xpath("//button[@type=\"submit\"]"))
          .get(0).click();

      Thread.sleep(WAIT_PERIOD_LONG);

      ////
      //// Each album url must be given separately. Anything else is not worth the
      //// loss of control
      ////
      List<String> subList;
      if (args.length > 1) {
        subList = Arrays.asList(args).subList(2, args.length);
      } else {
        subList = new ArrayList<>();
        subList.add("https://www.facebook.com/sindhu.gombi/photos");
      }

      // Unfortunately because Facebook downloading doesn't (easily) reveal the URL
      // and because it's not replayable context-free (you can't use wget because you
      // don't have the session cookie), our lambda will end with a void action rather
      // than a returned collection of values.
      subList.stream().flatMap(t -> {
        String facebookAlbumUrl = t;
        driver2.get(facebookAlbumUrl);
        try {
          Thread.sleep(5000L);
          return driver2.findElements(By.xpath("//a[@href]"))
              .stream();
        } catch (InterruptedException e1) {
          e1.printStackTrace();
          System.exit(-1);
          return null;
        }
      }).distinct().map(e -> {
        return e.getAttribute("href");
      }).filter(u -> {
        return u.contains("/photos");
      }).distinct().map(u -> { // find first theater link

        String facebookAlbumUrl = u;

        WebDriver driver = driver2;

        driver.get(u);
        try {
          Thread.sleep(4000L);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
          System.exit(-1);
          return "unreachable";
        }

        List<WebElement> aHrefElements = driver.findElements(
            By.xpath("//a[contains(@href,'photo.php')]"));

        if (aHrefElements.size() < 1) {
          aHrefElements = driver.findElements(
              By.xpath("//a[contains(@href,'/photo/')]"));
        }
        for (WebElement elem : aHrefElements) {
//          System.out
//              .println("[DEBUG] Headless.main() all photos in album: "
//                  + elem.getAttribute("href"));
        }
        // Exclude the profile photo link (I wish there was a more robust way to do
        // this)
        Stream<WebElement> stream;
        if (facebookAlbumUrl.contains("set=")) {
          String facebookAlbumSetId = facebookAlbumUrl
              .replaceAll(".*set=", "").replaceAll("&.*", "");
          stream = aHrefElements.stream()
              .filter(a -> !a.getAttribute("href").contains("__tn__"))
              .filter(a -> a.getAttribute("href")
                  .contains(facebookAlbumSetId));
        } else {
          stream = aHrefElements.stream().filter(
              a -> !a.getAttribute("href").contains("__tn__"));
        }
        System.err.println("[DEBUG] FacebookAlbums.main()");
        WebElement webElement = stream.collect(Collectors.toList())
            .get(0);
        webElement.click();
        try {
          Thread.sleep(4000L);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
          System.exit(-1);
          return null;
        }
        String firstTheaterImageUrl = driver.getCurrentUrl();
        return firstTheaterImageUrl;

      }).forEach(u -> {
        System.out.println(u);
      });
    }
  }
}
