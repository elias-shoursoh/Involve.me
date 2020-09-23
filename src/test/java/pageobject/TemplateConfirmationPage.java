package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/* Template Confirmation page - where a preview of the template can be made */
public class TemplateConfirmationPage extends BasePage {

	@FindBy(css = "div.e-use-template")
	private WebElement useTemplateBtn;
	@FindBy(css = "div.e-close img")
	private WebElement cancelBtn;
	@FindBy(css = "[title=\"desktop\"]")
	private WebElement desktopViewBtn;
	@FindBy(css = "[title=\"mobile\"]")
	private WebElement mobileViewBtn;
	@FindBy(css = ".btn")
	private WebElement findoutAndNextBtn;
	@FindBy(css = ".c-button.btn")
	private WebElement nextBtn;
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
	@FindBy(css = "div.e-headline .e-html-container")
	private WebElement feebackTxt;
	@FindBy(css = "p.feedback-text.correct")
	private WebElement correctAnswerMsg;
	@FindBy(css = "p.feedback-text")
	private WebElement wrongAnswerMsg;
	@FindBy(css = ".c-answer.answer-behaviour")
	private List<WebElement> asnwers;

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

	public void clickNext() {
		moveToElement(nextBtn);
		click(nextBtn);
	}

	@Step("Answer History's template tryout with {viewKind} view")
	public void tryHistoryTemplate(String viewKind) {
		switch (viewKind) {
		case "desktop":
			click(desktopViewBtn);
			break;
		case "mobile":
			click(mobileViewBtn);
			break;
		}
		click(findoutAndNextBtn);
		click(crusadesBtn);
		clickNext();
		click(waterGateBtn);
		clickNext();
		click(creationOfInternetBtn);
		clickNext();
		click(communistBtn);
		clickNext();
	}

	@Step("Answer a question in 90s Nostalgia quiz template with the {answer} answer")
	public void tryNostalgiaQuizTemplate(String answer) {
		clickNext();
		sleep(1000);
		List<WebElement> listOfAnswers = asnwers;
		switch (answer) {
		case "correct":
			click(listOfAnswers.get(2));
			break;
		case "wrong":
			click(listOfAnswers.get(0));
			break;
		}
	}

	@Step("Get feedback text after finishing template's questions")
	public String getFeedbackText() {
		return getText(feebackTxt);
	}

	// returns true if correct answer message is displayed
	public boolean isCorrectAnswerDisplayed() {
		return isElementDisplayed(correctAnswerMsg);
	}

	// returns true if wrong answer message is displayed
	public boolean isWrongAnswerDisplayed() {
		return isElementDisplayed(wrongAnswerMsg);
	}
}
