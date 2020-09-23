package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/* About page - the first page that appears when browsing to involve.me URL */
public class AboutPage extends BasePage {

	@FindBy(css = ".login")
	private WebElement loginLink;
	@FindBy(css = ".register")
	private WebElement registerLink;

	public AboutPage(WebDriver driver) {
		super(driver);
	}

	@Step("Clicking Login link")
	public void clickLoginLink() {
		click(loginLink);
	}

	@Step("Clicking Register link")
	public void clickRegisterLink() {
		click(registerLink);
	}
}
