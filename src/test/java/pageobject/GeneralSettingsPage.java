package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class GeneralSettingsPage extends TopNavigateBar {

	@FindBy(css = "div.flex.justify-between span")
	private WebElement publishBtn;
	@FindBy(css = "#project-name")
	private WebElement projectNameField;
	@FindBy(css = ".mb-4.hidden")
	private WebElement editProjectBtn;
	@FindBy(css = "#general-settings button span")
	private WebElement updateSettingsBtn;
	@FindBy(css = "#confirm-publish-button")
	private WebElement publishNowBtn;

	// constructor
	public GeneralSettingsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click Publish button and confirm publishing")
	public void publishProject() {
		click(publishBtn);
		click(publishNowBtn);
	}

	@Step("Click Update Settings button")
	public void clickUpdateSettings() {
		click(updateSettingsBtn);
	}

	@Step("click Edit Project button")
	public void clickEditProject() {
		click(editProjectBtn);
	}

	@Step("Editing project's name to {name} via General Setting page")
	public void editProjectName(String name) {
		fillText(projectNameField, name);
		clickUpdateSettings();
	}
}