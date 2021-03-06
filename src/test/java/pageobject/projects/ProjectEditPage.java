package pageobject.projects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;
import pageobject.basepage.BasePage;

/* Project Edit page - the page where adding to and editing projects is done */
public class ProjectEditPage extends BasePage {

	@FindBy(css = "input#project-name")
	private WebElement projectNameField;
	@FindBy(css = ".project-name")
	private WebElement projectName; // project name as displayed in page - upper left corner
	@FindBy(css = ".swal-button.swal-button--confirm")
	private WebElement startEditingBtn;
	@FindBy(css = "#bs-example-navbar-collapse-1 li:nth-child(4) a")
	private WebElement settingsLinkBtn;
	@FindBy(css = ".e-close.nav-link")
	private WebElement saveAndExitBtn;
	@FindBy(css = "#content-item-tab")
	private WebElement contentElementsBtn;
	@FindBy(css = "#tab-design-settings")
	private WebElement designBtn;
	@FindBy(css = ".btn-secondary.save-btn")
	private WebElement publishBtn;
	@FindBy(css = ".btn-secondary.preview-btn")
	private WebElement designPreviewBtn;
	@FindBy(css = ".e-page-item.current .add-page-container button")
	private WebElement addNewSlideBtn; // Add new slide button's locator is true only if the current slide is the one
										// before the last
	@FindBy(css = ".swal-button--confirm")
	private WebElement confirmDeleteSlideBtn;
	@FindBy(css = "[for='select-default']")
	private WebElement thankYouPageTypeBtn; // thank you page end slide
	@FindBy(css = "[for='select-outcomes']")
	private WebElement outcomePagesTypeBtn; // outcome page end slide
	@FindBy(css = "div.content-item-wrapper")
	private WebElement dropZone;
	@FindBy(css = ".has-rating")
	private WebElement ratingContent;
	@FindBy(css = "div.c-data-collection-container")
	private WebElement contactFormContent;
	@FindBy(css = ".content-item-edit-close .save-close")
	private WebElement saveAndCloseBtn; // appears after dragging an element to page
	@FindBy(css = ".e-save-succes")
	private WebElement noLinkWarningPopUp;
	@FindBy(css = ".swal-button--cancelCustom")
	private WebElement closeWarningPopUpBtn;
	@FindBy(css = "label#project-name-error")
	private WebElement projectNameErrorMsg; // in new project pop up
	@FindBy(css = ".new-project-modal")
	private WebElement newProjectPopUp;

	@FindBy(css = "div.content-menu-item")
	private List<WebElement> contentList; // the list of the content element offered to be added to any project
	@FindBy(css = ".e-pages-container .e-page-management")
	private List<WebElement> slidesList;
	@FindBy(css = "[title=\"Delete page\"]")
	private List<WebElement> deleteSlideBtns;

	private static int counter = 0;

	// constructor
	public ProjectEditPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click SAVE & EXIT button")
	public void clickSaveAndExit() {
		click(saveAndExitBtn);
	}

	@Step("Click SETTINGS button")
	public void clickSettings() {
		click(settingsLinkBtn);
	}

	@Step("Click PUBLISH button")
	public void clickPublish() {
		click(publishBtn);
		sleep(1000);
	}

	@Step("Open new project for editing - project name: {projectName}, project type: {projectType}")
	public void editProjectPrep(String projectName, String projectType) {
		fillText(projectNameField, projectName);
		switch (projectType) {
		case "thank you page":
			click(thankYouPageTypeBtn);
			break;
		case "outcome pages":
			click(outcomePagesTypeBtn);
			break;
		default:
			click(thankYouPageTypeBtn);
			break;
		}
		click(startEditingBtn);
	}

	@Step("Open new project with invalid name - {projectName}")
	public void editInvalidProjectName(String projectName) {
		switch (projectName) {
		case "":
			clearTextBox(projectNameField);
			click(startEditingBtn);
			break;
		default:
			fillText(projectNameField, projectName);
			break;
		}
	}

	@Step("Add the element {element} to project")
	public void addElementToProject(String element) {
		for (WebElement content : contentList) {
			if (getText(content).contains(element)) {
				if (counter == 0) {
					dragAndDrop(content, dropZone);
					counter++;
				} else {
					dragAndDrop(content, (dropZone.findElements(By.cssSelector(".content-item")).get(0)));
				}

				break;
			}
		}
		click(saveAndCloseBtn);
	}

	@Step("Selecting the slide that is one before the last")
	public void clickOneBeforeLastSlide() {
		List<WebElement> slides = slidesList;
		slides.get(slides.size() - 2).click();
	}

	@Step("Add new slide to project")
	public void addNewSlide() {
		click(addNewSlideBtn);
	}

	@Step("Delete slide number: {slideNumber}")
	public void deleteSlide(int slideNumber) {
		// will delete slide according to its given number
		click(deleteSlideBtns.get(slideNumber - 1));
		click(confirmDeleteSlideBtn);
	}

	@Step("Close no page link warning pop up")
	public void closeNoLinkWarningPopUp() {
		click(closeWarningPopUpBtn);
	}

	@Step("Get project's slides number")
	public int getSlidesNumber() {
		sleep(1000);
		List<WebElement> slides = slidesList;
		return slides.size();
	}

	@Step("Check if no page link warning popup is displayed")
	public boolean isNoLinkWarningMsgDisplayed() {
		return isElementDisplayed(noLinkWarningPopUp);
	}

	@Step("Check if content {content} is displayed on project")
	public boolean isContentAdded(String content) {
		switch (content) {
		case "Rating":
			return isElementDisplayed(ratingContent);
		case "Contact Form":
			return isElementDisplayed(contactFormContent);
		default:
			return false;
		}
	}

	@Step("Check if new project pop up is displayed")
	public boolean isNewProjectPopUpDisplayed() {
		return isElementDisplayed(newProjectPopUp);
	}

	@Step("Get project's name")
	public String getProjectName() {
		wait.until(ExpectedConditions.visibilityOf(projectName));
		return getText(projectName);
	}

	// returns the error message that is related to a new project's name
	public String getProjectNameErrorMsg() {
		return getText(projectNameErrorMsg);
	}
}
