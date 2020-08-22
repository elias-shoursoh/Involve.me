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
	@FindBy(css = "title=\"desktop\"")
	private WebElement desktopViewBtn;
	@FindBy(css = "[title=\"mobile\"]")
	private WebElement mobileViewBtn;
	@FindBy(css = ".btn")
	private WebElement findoutAndNextBtn;
	@FindBy(css = "[data-id=\"crrpv1w\"]")
	private WebElement crusadesBtn;
	@FindBy(css = "[data-id=\"9ti9ycv\"]")
	private WebElement waterGateBtn;
	@FindBy(css = "[data-id=\"v9hu5rk\"]")
	private WebElement creationOfInternetBtn;
	@FindBy(css = "[data-id=\"b111nuu\"]")
	private WebElement communistBtn;
	@FindBy(css = ".e-headline .e-html-container span")
	private WebElement thankYouMsg;
	@FindBy(css = "p.feedback-text")
	private WebElement feebackTxt;

	// TODO: Quizes have been changed here. should cancel the preview tryHistoryTemplate()

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

	@Step("Answer History's template tryout with {viewKind} view")
	public void tryHistoryTemplate(String viewKind) {
		switch (viewKind) {
		case "desktop":
			click(desktopViewBtn);
		case "mobile":
			click(mobileViewBtn);
		}
		click(desktopViewBtn);
		click(findoutAndNextBtn);
		click(crusadesBtn);
		click(findoutAndNextBtn);
		click(waterGateBtn);
		click(findoutAndNextBtn);
		click(creationOfInternetBtn);
		click(findoutAndNextBtn);
		click(communistBtn);
		click(findoutAndNextBtn);
	}

	@Step("Get feedback text after finishing template's questions")
	public String getFeedbackText() {
		return getText(feebackTxt);
	}
}
