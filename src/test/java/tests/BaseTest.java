package tests;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Configuration;

/*BaseTest class that will be inherited by all test classes; does setup and tear down for each test*/
public class BaseTest {

	WebDriver driver;

	@Parameters({ "debug" })
	@BeforeClass(description = "initializing driver and navigating to tested site url")
	public void setup(ITestContext testContext, boolean debug) throws MalformedURLException {
		if (debug) {
			createDriver(Configuration.readProperty("browserType"));
		} else {
			createRemoteDriver(Configuration.readProperty("browserType"));
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		testContext.setAttribute("WebDriver", this.driver);
		driver.get(Configuration.readProperty("TestedSiteUrl"));
	}

	@AfterClass(description = "closing driver")
	public void tearDown() {
		driver.quit();
	}

	// method that handles driver type according to the required browser string
	private void createDriver(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;

		default:// open Chrome browser by default in case no string matched
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}

	private void createRemoteDriver(String browser) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		switch (browser) {
		case "chrome":
			capabilities.setBrowserName("chrome");
			capabilities.setVersion("85.0");
			break;
		case "firefox":
			capabilities.setBrowserName("firefox");
			capabilities.setVersion("79.0");
			break;
		default:
			capabilities.setBrowserName("chrome");
			capabilities.setVersion("85.0");
			break;
		}
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("enableVideo", true);
		capabilities.setCapability("videoName", this.getClass().getName() + "_"
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss")));
		capabilities.setCapability("screenResolution", "1280x1024x24");
		driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
	}
}
