package pageobject;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*Base class that wraps selenium operations and will be inherited by all POMs*/
public class BasePage {

	WebDriver driver;
	Alert alert;
	JavascriptExecutor js;
	protected WebDriverWait wait;

	// constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	// click operation
	public void click(WebElement el) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
		highlightElement(el, "green");
		el.click();
	}

	// send keys operation
	public void fillText(WebElement el, String txt) {
		wait.until(ExpectedConditions.visibilityOf(el));
		highlightElement(el, "green");
		el.clear();
		el.sendKeys(txt);
	}
	
	// clears text field
	public void clearTextBox(WebElement el) {
		highlightElement(el, "green");
		el.clear();
	}

	// submit text
	public void submit(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
		el.submit();
	}

	// move to a given element
	public void moveToItem(WebElement el) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el).perform();
	}

	// get text operation
	public String getText(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
		highlightElement(el, "green");
		return el.getText();
	}

	// returns Select object
	public Select select(WebElement selection) {
		Select select = new Select(selection);
		return select;
	}

	// sleeps for a given amount of time
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// accepts alert for deletion functionality
	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();
	}

	// returns webelements list according to css selector
	public List<WebElement> findElemList(String selector) {
		sleep(500);
		return driver.findElements(By.cssSelector(selector));
	}

	// returns true if given element is displayed on page
	public boolean isElementDisplayed(WebElement el) {
		try {
			return el.isDisplayed();
		} catch (StaleElementReferenceException ex) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// highlights element according to a given color
	private void highlightElement(WebElement element, String color) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:yellow;border: 1px solid " + color + ";" + originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after 400 milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);
	}
}
