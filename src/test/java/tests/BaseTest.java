package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Configuration;

/*BaseTest class that will be inherited by all test classes; does setup and tear down for each test*/
public class BaseTest {

	WebDriver driver;

	@BeforeClass(description = "initializing driver and navigating to tested site url")
	public void setup(ITestContext testContext) {
		createDriver(Configuration.readProperty("browserType"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
}
