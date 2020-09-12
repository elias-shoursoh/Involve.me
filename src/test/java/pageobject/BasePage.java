package pageobject;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
		try {
			wait.until(ExpectedConditions.elementToBeClickable(el));
		} finally {
			highlightElement(el, "green");
			el.click();
		}
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
//		String xto = Integer.toString(dropZone.getLocation().x);
//		String yto = Integer.toString(dropZone.getLocation().y);
//		js = (JavascriptExecutor) driver;
//		js.executeScript(
//				"function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; "
//						+ "simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
//				dropZone, xto, yto);

//		Actions builder = new Actions(driver);
//		Action dragAndDrop = builder.clickAndHold(el)
//				.moveByOffset(dropZone.getLocation().getX(), dropZone.getLocation().getY()).release(dropZone).build();
//		dragAndDrop.perform();
		Actions actions = new Actions(driver);
		actions.dragAndDrop(el, dropZone).perform();
		//actions.dragAndDropBy(el, 1545, 833).build().perform();
//		sleep(1000);
//		moveToElement(dropZone);
		// actions.click().perform();
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
		js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after 400 milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);
	}
}
