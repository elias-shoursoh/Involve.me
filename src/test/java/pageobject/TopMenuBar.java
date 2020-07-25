package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class TopMenuBar extends BasePage {

	@FindBy(css = ".login")
	private WebElement loginLink;
	@FindBy(css = ".register")
	private WebElement registerLink;

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
