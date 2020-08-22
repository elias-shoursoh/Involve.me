package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class FirstTimeLogInPage extends BasePage {

	@FindBy(css = "#_eivm32oa8")
	private WebElement frame;
	@FindBy(css = ".answer-behaviour")
	private List<WebElement> templateSelectionList;
	@FindBy(css = ".answer-behaviour")
	private List<WebElement> needsList;
	@FindBy(css = ".answer-behaviour")
	private List<WebElement> teacherAnswers;
	@FindBy(css=".alert-success")
	private WebElement successMsg;

	// constructor
	public FirstTimeLogInPage(WebDriver driver) {
		super(driver);
	}

	@Step("Manage all post registeration question survey when template is {templateName}, need is {needType} and teacher status is {isTeacher}")
	public void manageWelcomePage(String templateName, String needType, String isTeacher) {
		// switching to welcome page frame
		switchToFrame(frame);
		for (WebElement template : templateSelectionList) {
			if (getText(template).equalsIgnoreCase(templateName)) {
				click(template);
				break;
			}
		}
		for (WebElement need : needsList) {
			if (getText(need).equalsIgnoreCase(needType)) {
				click(need);
				break;
			}
		}
		switch (isTeacher) {
		case "yes":
			click(teacherAnswers.get(0));
		case "no":
			click(teacherAnswers.get(1));
		default:
			click(teacherAnswers.get(1));
		}
		sleep(2000);
		// go back to original HTML page
		switchOutOfFrame();

	}
}
