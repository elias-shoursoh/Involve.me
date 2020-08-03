package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class TemplatesPage extends BasePage {

	@FindBy(css = "a#filter-all")
	private WebElement allCategory;
	@FindBy(css = "a#filter-all span")
	private WebElement allCatNumber;
	@FindBy(css = "#filter-quiz")
	private WebElement quizCategory;
	@FindBy(css = "#filter-quiz span")
	private WebElement quizCatNumber;
	@FindBy(css = "#filter-survey")
	private WebElement surveyCategory;
	@FindBy(css = "#filter-survey span")
	private WebElement surveyCatNumber;
	@FindBy(css = "#filter-calculator")
	private WebElement calculatorCategory;
	@FindBy(css = "#filter-calculator span")
	private WebElement calculatorCatNumber;
	@FindBy(css = "#filter-form")
	private WebElement formCategory;
	@FindBy(css = "#filter-form span")
	private WebElement formCatNumber;
	@FindBy(css = "#filter-payment")
	private WebElement paymentCategory;
	@FindBy(css = "#filter-payment span")
	private WebElement paymentCatNumber;
	@FindBy(css = "#filter-leadpage")
	private WebElement leadPageCategory;
	@FindBy(css = "#filter-leadpage span")
	private WebElement leadPageCatNumber;
	@FindBy(css = "#filter-promotion")
	private WebElement promotionCategory;
	@FindBy(css = "#filter-promotion span")
	private WebElement promotionCatNumber;
	@FindBy(css = "#filter-personality_test")
	private WebElement personalityTestCategory;
	@FindBy(css = "#filter-personality_test span")
	private WebElement personalityTestCatNumber;
	@FindBy(css = "#template-gallery tbody tr")
	private List<WebElement> templatesBlocks;
	
	// TODO: Make this to be By object??
	private String chooseBtns = "tr .btn.btn-primary";

	// constructor
	public TemplatesPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click All category")
	public void clickAll() {
		click(allCategory);
	}

	@Step("Click Quiz category")
	public void clickQuiz() {
		click(quizCategory);
	}

	@Step("Click Survey category")
	public void clickSurvey() {
		click(surveyCategory);
	}

	@Step("Click Calculator category")
	public void clickCalculator() {
		click(calculatorCategory);
	}

	@Step("Click Form category")
	public void clickForm() {
		click(formCategory);
	}

	@Step("Click Payment Form category")
	public void clickPaymentForm() {
		click(paymentCategory);
	}

	@Step("Click Lead Page category")
	public void clickLeadPage() {
		click(leadPageCategory);
	}

	@Step("Click Personality Test category")
	public void clickPromotion() {
		click(promotionCategory);
	}

	@Step("Click All category")
	public void clickPersonalityTest() {
		click(personalityTestCategory);
	}

	@Step("Get number of displayed templates in page")
	public int getTemplatesNumber() {
		return templatesBlocks.size();
	}
}
