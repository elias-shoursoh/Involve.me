package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class PublishPage extends BasePage {

	@FindBy(css = "h1.e-title")
	private WebElement shareProjectTitle;
	@FindBy(css = ".btn.btn-outline-secondary")
	private WebElement backToOverviewBtn;

	// constructor
	public PublishPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click Back to overview button to go back to projects page")
	public void clickBackToOverview() {
		click(backToOverviewBtn);
	}

	public boolean isTitleDispalyed() {
		return isElementDisplayed(shareProjectTitle);
	}
}
