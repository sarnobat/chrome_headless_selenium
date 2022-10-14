import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Joiner;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Monolith. The only thing I can get to work, but selecting the right links is
 * messy.
 */
public class FacebookImages {
//new String[] {
//"https://www.facebook.com/varsha.rohidekar.9/photos_by"
//"https://www.facebook.com/sindhu.gombi/photos_by",
//"https://www.facebook.com/sindhu.gombi/photos_of"
//"https://www.facebook.com/media/set/?set=a.103236316503075&type=3"
//"https://www.facebook.com/media/set/?set=a.103236316503075&type=3"
// "https://www.facebook.com/media/set/?set=a.418968131596557&type=3"
// "https://www.facebook.com/media/set/?set=a.103900176436689&type=3"
//"https://www.facebook.com/media/set/?set=a.220906377933744&type=3",
//"https://www.facebook.com/media/set/?set=a.289379861086395&type=3",
//"https://www.facebook.com/media/set/?set=a.153583031332746&type=3",
//"https://www.facebook.com/media/set/?set=a.549330278424684&type=3",
//"https://www.facebook.com/media/set/?set=a.589142874443424&type=3",
//"https://www.facebook.com/media/set/?set=a.558416390849406&type=3",
//"https://www.facebook.com/media/set/?set=a.294639280560453&type=3",
//"https://www.facebook.com/media/set/?set=a.278720775485637&type=3",
//"https://www.facebook.com/media/set/?set=a.275431262481255&type=3",
//"https://www.facebook.com/media/set/?set=a.100979533259763&type=3"
//}

  private static void extracted2(WebDriver driver, int i)
      throws InterruptedException, MalformedURLException,
      IOException {
    List<WebElement> elems2 = extracted(driver);
    if (elems2.size() > 0) {
      extracted(i, elems2);
    } else {
      extracted(driver, i);
    }
  }

  private static String extracted(String theaterPageUrl) {
    String currentImageSetId = theaterPageUrl.replaceAll(".*set=", "")
        .replaceAll("&.*", "");
    System.out
        .println("FacebookImages.main() set = " + currentImageSetId);
    return currentImageSetId;
  }

  private static List<WebElement> extracted(WebDriver driver)
      throws InterruptedException {
    List<WebElement> elems = driver.findElements(By.xpath(
        "//div[contains(@aria-label,'Actions for this post')]"));
    elems.get(0).click();
    Thread.sleep(WAIT_PERIOD);
    List<WebElement> elems2 = driver
        .findElements(By.xpath("//a[@download]"));
    return elems2;
  }

  private static void extracted(WebDriver driver, int i)
      throws MalformedURLException, IOException {
    System.out
        .println("[DEBUG] Headless.main() download non-original");

    List<WebElement> elems3 = driver.findElements(By.xpath(
        "//img[contains(@data-visualcompletion,'media-vc-image')]"));
    String sourceURL = elems3.get(0).getAttribute("src");
    URL url = new URL(sourceURL);
    String fileName = sourceURL.substring(
        sourceURL.lastIndexOf('/') + 1, sourceURL.length());
    Path targetPath = new File(
        System.getProperty("user.home") + "/Downloads/"
            + File.separator + fileName.replaceFirst("\\?.*", ""))
                .toPath();
    Files.copy(url.openStream(), targetPath,
        StandardCopyOption.REPLACE_EXISTING);
    System.out.println("[DEBUG] Headless.main() " + i
        + ") non-full size saved to " + targetPath);
  }

  private static void extracted(int i, List<WebElement> elems2)
      throws InterruptedException {
    WebElement webElement = elems2.get(0);
    System.out
        .println("FacebookImages.main() this throws an exception: "
            + webElement);
    webElement.click();
    System.out.println(
        "FacebookImages.main() this did not throw an exception");
    String hrefFullSize = webElement.getAttribute("href");
    System.out.println("[DEBUG] Headless.main() " + i
        + ") full size saved to " + hrefFullSize);
    Thread.sleep(WAIT_PERIOD);
  }
  private static final long WAIT_PERIOD = 4000L;
  private static final long WAIT_PERIOD_LONG = 6000L;

  public static void main(String[] args) throws URISyntaxException,
      JSONException, IOException, InterruptedException {
    System.err.println("[DEBUG] Headless.main() - args = " + args);
    WebDriverManager.chromedriver().setup();
    ChromeOptions co = new ChromeOptions();
    co.addArguments("--disable-notifications");
    WebDriver driver = new ChromeDriver(co);
    {
      String baseUrl2 = "https://www.facebook.com";

      // launch Fire fox and direct it to the Base URL
      driver.get(baseUrl2);
      login: {
        new Actions(driver).sendKeys(driver
            .findElements(By.xpath("//input[@id=\"email\"]")).get(0),
            "ss533@cornell.edu").perform();
        new Actions(driver).sendKeys(driver
            .findElements(By.xpath("//input[@id=\"pass\"]")).get(0),
            args[0]).perform();
        driver.findElements(By.xpath("//button[@type=\"submit\"]"))
            .get(0).click();

        Thread.sleep(WAIT_PERIOD_LONG);
      }
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

      // TODO: Read from stdin instead. And print the input line on each output line
      // so that each line is self contained
      // Selenium Facebook Crawl before scrape (Dilip, Pavan, Ajay, Poornima, Megha P,
      // Veena, Jayaprakash)

      List<String> subList;
      if (args.length > 1) {
        subList = Arrays.asList(args).subList(2, args.length);
      } else {
        subList = new ArrayList<>();
//        subList.add("https://www.facebook.com/sindhu.gombi/photos");
        subList.add(
        // NONE: https://www.facebook.com/profile.php?id=100008201263675&sk=photos
        // NONE: https://www.facebook.com/shyam.gombi/photos
        // madhavi
        // vijay
//              "https://www.facebook.com/profile.php?id=100011332900918"
//            "https://www.facebook.com/amit.bengeri/photos"
//            "https://www.facebook.com/vallabh.vasudevan.5/photos"
//            "https://www.facebook.com/kiran.rohidekar/photos"
//            "https://www.facebook.com/prasanna.kulkarni.7798/photos"
            "https://www.facebook.com/nikhil.rohidekar"
//            "https://www.facebook.com/rashmi.nidhi.1"
//            "https://www.facebook.com/jeevan.bharadwaj.3"
//            "https://www.facebook.com/anuradha.rohidekar/photos"
//            "https://www.facebook.com/veena.jayaprakash.94/photos"
//            "https://www.facebook.com/veena.jayaprakash.77/photos"
//            "https://www.facebook.com/ajay.rohidekar/photos_albums"
//            "https://www.facebook.com/soudamini.gombi/photos",
//            "https://www.facebook.com/jayaprakashha/photos_by"
//            "https://www.facebook.com/gururaj.rohidekar"
//            "https://www.facebook.com/rohidekar.bandhugalu"
//            "https://www.facebook.com/parimala.gombi.9"
//            "https://www.facebook.com/krishnamurthy.rohidekar"
//            "https://www.facebook.com/srinidhidn"

//            "https://www.facebook.com/jayaprakash.prakash.9828/photos"
        );
      }

      if (subList.size() == 1) {

        String profileURL = subList.get(0).replaceFirst("(/photos)?$",
            "/photos");

        if (!profileURL.endsWith("/photos")) {
          System.err.println(
              "[ERROR] FacebookImages.main() not all albums will get downloaded.");
          System.exit(-1);
          driver.close();
        }

        // Unfortunately because Facebook downloading doesn't (easily) reveal the URL
        // and because it's not replayable context-free (you can't use wget because you
        // don't have the session cookie), our lambda will end with a void action rather
        // than a returned collection of values.
        List<String> albumLinks = subList.stream().flatMap(t -> {
          String facebookAlbumUrl = t;
          driver.get(facebookAlbumUrl);
          try {
            Thread.sleep(5000L);
            return driver.findElements(By.xpath("//a[@href]"))
                .stream();
          } catch (InterruptedException e1) {
            e1.printStackTrace();
            driver.close();
            System.exit(-1);
            return null;
          }
        }).distinct().map(e -> {
//          System.err.println("[DEBUG] FacebookImages.main(): all hrefs: " + e.getAttribute("href"));
          return e.getAttribute("href");
        }).filter(u -> {
          if (u.contains("sk=photo")) {
            return true;
          }
          return u.contains("/photos") && !u.contains("/photos/");
        }).distinct().collect(Collectors.toList());

        if (albumLinks.size() < 2) {
          System.err.println(
              "[ERROR] FacebookImages.main() not all albums will get downloaded. Strange, this should have been fixed.");
          System.exit(-1);
          driver.close();
        }
        Collections.shuffle(albumLinks);
        System.err.println("[DEBUG] FacebookImages.main() 2");
        List<String> albumLinks2 = new LinkedList<>();
        for (String a : albumLinks) {
          if (a.contains("photos_albums")) {
            driver.get(a);
            Thread.sleep(1000L);
            ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,850)", "");
            Thread.sleep(1000L);

            List<WebElement> aHrefElements = driver.findElements(
                By.xpath("//a[contains(@href,'media/set')]"));
            for (WebElement elem : aHrefElements) {
              albumLinks2.add(elem.getAttribute("href"));
            }
          } else {
//          return Stream.of(u).collect(Collectors.toList());
            albumLinks2.add(a);
          }
        }
        System.err
            .println("[DEBUG] FacebookImages.main() albumLinks2 = "
                + albumLinks2.size());
        {
//      ).collect(Collectors.toList());;
//        

          if (true) {
            System.err.println("[DEBUG] FacebookImages.main() "
                + Joiner.on("\n").join(albumLinks2));
//          driver.close();
//          System.exit(-1);
          }
//        albumLinks.stream()
        }
        int i = 0;
        Collections.shuffle(albumLinks2);
        for (String albumUrl : albumLinks2

        ) {
          ++i;
          System.err.printf(
              "[DEBUG] FacebookImages.main() - loading next album: %d) %s\n",
              i, albumUrl);
          driver.get(albumUrl);
          System.err.println("[DEBUG] FacebookImages.main() waiting");
          Thread.sleep(WAIT_PERIOD_LONG);
          System.err.println(
              "[DEBUG] FacebookImages.main() finished waiting");
          {
            clickFirstElement: {
              List<WebElement> aHrefElements = driver.findElements(
                  By.xpath("//a[contains(@href,'photo.php')]"));

              if (aHrefElements.size() < 1) {
                aHrefElements = driver.findElements(
                    By.xpath("//a[contains(@href,'/photo/')]"));
              }
              for (WebElement elem : aHrefElements) {
                System.err.println(
                    "[DEBUG] Headless.main() all photos in album: "
                        + elem.getAttribute("href"));
              }
              System.err.println(
                  "[DEBUG] FacebookImages.main() Found photo elements: "
                      + aHrefElements.size());
              // Exclude the profile photo link (I wish there was a more robust way to do
              // this)
              Stream<WebElement> stream;
              if (albumUrl.contains("set=")) {
                String facebookAlbumSetId = albumUrl
                    .replaceAll(".*set=", "").replaceAll("&.*", "");
                stream = aHrefElements.stream().filter(
                    a -> !a.getAttribute("href").contains("__tn__"))
                    .filter(a -> a.getAttribute("href")
                        .contains(facebookAlbumSetId));
              } else {
                System.err.println(
                    "[DEBUG] FacebookImages.main() not a set url: "
                        + albumUrl);
                stream = aHrefElements.stream().filter(
                    a -> !a.getAttribute("href").contains("__tn__"));
              }
              List<WebElement> collect = stream
                  .collect(Collectors.toList());
              System.err
                  .println("[DEBUG] FacebookImages.main() collect = "
                      + collect.size());
              collect.get(0).click();
            } // end clickFirstElement
            Thread.sleep(WAIT_PERIOD_LONG);
          }
          boolean nextPhotoExists = true;
          
          Set<String> visitedUrls = new HashSet<>();
          while (nextPhotoExists) {
            Thread.sleep(WAIT_PERIOD);
            String theaterPageUrl = driver.getCurrentUrl();
            if (visitedUrls.contains(theaterPageUrl)) {
              System.err.println(
                  "[INFO] Headless.main() Made full cycle through images");
              break;
            } else {
              visitedUrls.add(theaterPageUrl);
            }
            System.out.printf("%-70s %70s  %s\n", profileURL,
                albumUrl, theaterPageUrl);
            if (false) {
              saveImageToDisk: {

                boolean proceed = true;
                if (theaterPageUrl.contains("set=")) {
                  String facebookAlbumSetId = albumUrl
                      .replaceAll(".*set=", "").replaceAll("&.*", "");
                  String currentImageSetId = extracted(
                      theaterPageUrl);
                  if (currentImageSetId.equals(facebookAlbumSetId)) {
                    proceed = true;
                  } else {
                    // this page is not part of the current album, I can't find a clean way to
                    // exclude it.
                    proceed = false;
                    System.out.printf(
                        "FacebookImages.main() %s is not part of set %s \n",
                        currentImageSetId, facebookAlbumSetId);
                  }
                }
                if (proceed) {
                  if (visitedUrls.contains(theaterPageUrl)) {
                    System.out.println(
                        "[INFO] Headless.main() Made full cycle through images");
                    break;
                  } else {
                    visitedUrls.add(theaterPageUrl);
                  }
                  try {
                    extracted2(driver, i);
                  } catch (Exception e) {
                    e.printStackTrace();
                  }
                } else {
                }

              }
            }
            driver.findElements(By.xpath("//body")).get(0)
                .sendKeys(Keys.RIGHT);
          }
        }
      }
      if (false) {
        System.err.println(
            "[DEBUG] Headless.getGeneratedHtml() - Starting Chrome (Xvfb needs to be running on the same port as DISPLAY) ");
      }
      // get the actual value of the title
      driver.close();
    }
  }

}
