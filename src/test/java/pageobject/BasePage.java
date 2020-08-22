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

/*Base class that wraps Selenium operations and will be inherited by all POMs*/
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
	public void moveToElement(WebElement el) {
		Actions actions = new Actions(driver);
		actions.moveToElement(el).perform();
	}

	// drag and drop elements
	public void dragAndDrop(WebElement el, WebElement dropZone) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(el, dropZone).build().perform();
		sleep(1000);
		// TODO: fix the offset here.
		actions.moveByOffset(725, 268).contextClick().perform();
		sleep(1000);
		actions.click();
	}

	// get text operation
	public String getText(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
		highlightElement(el, "green");
		return el.getText();
	}

	// get element from a web element list according to a passed string
	public WebElement getElementFromListByText(List<WebElement> list, By locator, String txt) {
		for (WebElement elem : list) {
			if (locator != null) { // in case locator is required
				if (getText(elem.findElement(locator)).equalsIgnoreCase(txt)) {
					return elem;
				} else { // in case no locator is required
					if (getText(elem).equalsIgnoreCase(txt)) {
						return elem;
					}
				}
			}
		}
		return null;
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

	// switch to frame
	public void switchToFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}

	// back to the main HTML page
	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}

	// returns web elements list of a certain passed wrapper web element according
	// to a By object
	public List<WebElement> getElemList(WebElement wrapper, By locator) {
		sleep(500);
		return wrapper.findElements(locator);
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
