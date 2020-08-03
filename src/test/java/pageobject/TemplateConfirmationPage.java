package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class TemplateConfirmationPage extends BasePage {

	@FindBy(css = "div.e-use-template")
	private WebElement useTemplateBtn;
	@FindBy(css = "div.e-close img")
	private WebElement cancelBtn;

//TODO: take a default preview quiz in desktop form and in mobile form
	// constructor
	public TemplateConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click Use Template Button")
	public void clickUseTemplate() {
		click(useTemplateBtn);
	}

	@Step("Cancel using template")
	public void clickCancel() {
		click(cancelBtn);
	}
}
