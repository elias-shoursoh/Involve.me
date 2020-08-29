package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class TemplatesPage extends TopNavigateBar {

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

	// selectors for each template
//	private By chooseBtns = By.cssSelector("a .btn.btn-primary");
//	private By templateTitle = By.cssSelector("div.details-container");

	// Choose button's name
//	private String choose = "choose";

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

	@Step("Select template {template}")
	public void chooseTemplate(String templateName) {
		List<WebElement> templates = templatesBlocks;
		for (WebElement template : templates) {
			if (getText(template).contains(templateName)) {
				WebElement btn = template.findElement(By.cssSelector("a .btn.btn-primary"));
				moveToElement(btn);
				click(btn);
				break;
			}
		}
//		WebElement btn = getTemplateChooseBtn(choose, templateName);
//		moveToElement(btn);
//		click(btn);
	}

	@Step("Get number of displayed templates in page")
	public int getTemplatesNumber() {
		return templatesBlocks.size();
	}

	@Step("Get the number displayed next to category name {catName}")
	public int getCategoryDisplayedNumber(String catName) {
		switch (catName) {
		case "all":
			return Integer.parseInt(getText(allCatNumber));
		case "quiz":
			return Integer.parseInt(getText(quizCatNumber));
		case "survey":
			return Integer.parseInt(getText(surveyCatNumber));
		case "calculator":
			return Integer.parseInt(getText(calculatorCatNumber));
		case "form":
			return Integer.parseInt(getText(formCatNumber));
		case "payment form":
			return Integer.parseInt(getText(paymentCatNumber));
		case "lead page":
			return Integer.parseInt(getText(leadPageCatNumber));
		case "promotion":
			return Integer.parseInt(getText(promotionCatNumber));
		case "personality test":
			return Integer.parseInt(getText(personalityTestCatNumber));
		default:
			return 0;
		}
	}

	// method that returns the requested Choose button's element for the required
	// template
//	private WebElement getTemplateChooseBtn(String BtnName, String templateName) {
//		List<WebElement> eBtns = getElemList(getElementFromListByText(templatesBlocks, templateTitle, templateName),
//				chooseBtns);
//		return getElementFromListByText(eBtns, null, BtnName);
//	}
}
