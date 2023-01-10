import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;

@Deprecated // Use FacebookImages.java
public class Headless {

	//	private static final String CHROMEDRIVER_PATH = "/Users/ssarnobat/github/chrome_headless/chromedriver";

	 //private static final String CHROMEDRIVER_PATH = "/home/sarnobat/github/chrome_headless/chromedriver_linux64";

	private static List<String> getGeneratedHtml(String binary) throws MalformedURLException, IOException {
		

		// Don't use the chrome binaries that you browse the web with.
		System.setProperty("webdriver.chrome.driver", binary);
		System.setProperty("webdriver.chrome.logfile", "/dev/null");
		System.setProperty("webdriver.chrome.args", "disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// HtmlUnitDriver and FirefoxDriver didn't work. Thankfully
		// ChromeDriver does
		System.err.println("Headless.getGeneratedHtml() - Starting Chrome (Xvfb needs to be running on the same port as DISPLAY) ");
		WebDriver driver = new ChromeDriver();
		List<String> ret = ImmutableList.of();
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8));
			// okay, I guess Charsets.UTF_8 is Guava, but that lets us not worry about
			// catching UnsupportedEncodingException
			System.err.println("Headless.getGeneratedHtml() - waiting for stdin");
			while (reader.ready()) {
				try {
					String line = reader.readLine();
					String url1 = line;
					System.err.println("Headless.getGeneratedHtml() - url1 = " + url1);
					String url = url1.startsWith("http") ? url1 : "http://" + url1;
					
					driver.get(url);
			        System.err.println("Headless.getGeneratedHtml() - URL requested, waiting 5 seconds for reply." + url);
					// TODO: shame there isn't an input stream, then we wouldn't
					// have to store the whole page in memory
					try {
						// We need to let the dynamic content load.
						Thread.sleep(5000L);
						System.err.println("Headless.getGeneratedHtml() - Finished sleeping. " + url);
					} catch (InterruptedException e) {
						System.err.println("Headless.getGeneratedHtml() - Caught InterruptedException " + url);
						e.printStackTrace();
					}
					String source = driver.getPageSource();
					System.out.println(source);
				} catch(Exception e) {
					System.err.println("Headless.getGeneratedHtml() - Exception 2: " + e + ". ");
				} finally {
					System.err.println("Headless.getGeneratedHtml() - Finally 1: ");
					//driver.quit();
				}
				
			}
		} catch(Exception e) {
			System.err.println("Headless.getGeneratedHtml() - Exception 2: " + e + ". ");
		} finally {
			System.err.println("Headless.getGeneratedHtml() - Finally 2: ");
			driver.quit();
		}
		System.err.println("Headless.getGeneratedHtml() - Returning: ");
		return ret;
	}

	public static void main(String[] args) throws URISyntaxException, JSONException, IOException {
		System.err.println("Headless.main() - args = " + args);
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charsets.UTF_8));
//		// okay, I guess Charsets.UTF_8 is Guava, but that lets us not worry about
//		// catching UnsupportedEncodingException
//		while (reader.ready()) {
//		  String line = reader.readLine();
		  getGeneratedHtml(args[0]);
//		}
		
	}
}
