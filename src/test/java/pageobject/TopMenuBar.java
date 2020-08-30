package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Top menu bar POM*/
public class TopMenuBar extends BasePage {

	@FindBy(css = "[href=\"https://app.involve.me/login\"]")
	private WebElement loginLink;
	@FindBy(css = "#frontend-navbar-collapse [href=\"https://app.involve.me/register\"]")
	private WebElement registerLink;

	// constructor
	public TopMenuBar(WebDriver driver) {
		super(driver);
	}

	@Step("Click on login link button")
	public void clickLogin() {
		click(loginLink);
	}

	@Step("Click on Register link button")
	public void clickRegister() {
		click(registerLink);
	}
}
