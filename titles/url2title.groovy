import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;

public class Headless {

	// private static final String CHROMEDRIVER_PATH =
	// "/Users/ssarnobat/github/chrome_headless/chromedriver";

	// private static final String CHROMEDRIVER_PATH =
	// "/home/sarnobat/github/chrome_headless/chromedriver_linux64";
	private static final ExecutorService pool = Executors.newFixedThreadPool(1);

	private static List<String> getGeneratedHtml(String binary) throws MalformedURLException,
			IOException {

		// Don't use the chrome binaries that you browse the web with.
		System.setProperty("webdriver.chrome.driver", binary);
		System.setProperty("webdriver.chrome.logfile", "/dev/null");
		System.setProperty("webdriver.chrome.args", "disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// HtmlUnitDriver and FirefoxDriver didn't work. Thankfully
		// ChromeDriver does
		System.err
				.println("Headless.getGeneratedHtml() - Starting Chrome (Xvfb needs to be running on the same port as DISPLAY) ");
		final WebDriver driver = new ChromeDriver();
		List<String> ret = ImmutableList.of();
		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,
					Charsets.UTF_8));
			// okay, I guess Charsets.UTF_8 is Guava, but that lets us not worry
			// about
			// catching UnsupportedEncodingException
			System.err.println("Headless.getGeneratedHtml() - waiting for stdin");
			while (reader.ready()) {
				try {
					String line = reader.readLine();
					String url1 = line;
					System.err.println("Headless.getGeneratedHtml() - url1 = " + url1);
					final String url = url1.startsWith("http") ? url1 : "http://" + url1;
					int timeout = 60;
					try {
						Future<String> printTitle = pool.submit(new Callable<String>() {
							@Override
							public String call() throws Exception {
								try {
								driver.get(url);
								} catch (WebDriverException e) {
									System.err.println(url + " problem: "+ e.getMessage());
								}
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
//								System.err.println("Headless.getGeneratedHtml() - 10. " + url);
								String source = driver.getPageSource();// .replaceAll(".*<title>(.*?)</title>.*");
								Pattern p = Pattern.compile(".*<title>(.*?)</title>.*");
								Matcher m = p.matcher(source);
								String x;
//								System.err.println("Headless.getGeneratedHtml() - 11. " + url);
								if (m.find()) {
									x = url + " :: " + m.group(1);
									// System.out.println(x);
								} else {
									// System.out.println(source);
									x = url + " :: (Could not determine)";
								}
								if (url.equals("http")) {
									System.err.println("Headless.getGeneratedHtml() - bad title. Remove from input.");
									System.exit(-1);
								}
//								System.err.println("Headless.getGeneratedHtml() - 12. " + url);
								return x;
							}
						});
						String title = Futures.get(printTitle, timeout, TimeUnit.SECONDS,
								TimeoutException.class);
//						System.err.println("Did not time out: " + timeout + " seconds");
						//System.out.println("Did not time out: " + timeout + " seconds");
						System.out.println(title);
					} catch (TimeoutException e) {
						System.err.println("Headless.getGeneratedHtml() - Timed out: " + timeout + " seconds");
						System.out.println(url + " :: (timeout)");
//						System.out.println("Timed out: " + timeout + " seconds");
//						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Headless.getGeneratedHtml() - Exception 2: " + e + ". ");
				} finally {
					System.err.println("Headless.getGeneratedHtml() - Finally 1: Finished visiting all URLs in batch.");
					// driver.quit();
				}

			}
		} catch (Exception e) {
			System.err.println("Headless.getGeneratedHtml() - Exception 2: " + e + ". ");
		} finally {
			System.err
					.println("Headless.getGeneratedHtml() - Finally 2: quitting headless browser. ");
			driver.quit();
		}
		System.err.println("Headless.getGeneratedHtml() - Returning: script ended.");
		pool.shutdown();
		return ret;
	}

	public static void main(String[] args) throws URISyntaxException, JSONException, IOException {
		// System.err.println("Headless.main() - args = " + args);
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(System.in, Charsets.UTF_8));
		// // okay, I guess Charsets.UTF_8 is Guava, but that lets us not worry
		// about
		// // catching UnsupportedEncodingException
		// while (reader.ready()) {
		// String line = reader.readLine();
		getGeneratedHtml(args[0]);
//		System.err.println("Headless.main() end");
		// }

	}
}
