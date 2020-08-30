package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*About page - the first page that appears when browsing to involve.me URL*/
public class AboutPage extends BasePage {

	@FindBy(css = ".login")
	private WebElement loginLink;
	@FindBy(css = ".register")
	private WebElement registerLink;

	public AboutPage(WebDriver driver) {
		super(driver);
	}

	public void clickLoginLink() {
		click(loginLink);
	}

	public void clickRegisterLink() {
		click(registerLink);
	}
}
