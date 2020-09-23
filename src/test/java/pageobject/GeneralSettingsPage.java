package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

/* General Settings page */
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
	@FindBy(css = "p.block a")
	private WebElement publishAnywayView;

	// constructor
	public GeneralSettingsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click Publish button and confirm publishing")
	public void publishProject() {
		click(publishBtn);
		click(publishNowBtn);
		try {
			wait.until(ExpectedConditions.visibilityOf(publishAnywayView));
			click(publishNowBtn);
		} catch (Exception e) {
			// pass
		}
	}

	@Step("Click Update Settings button")
	public void clickUpdateSettings() {
		click(updateSettingsBtn);
	}

	@Step("click Edit Project button")
	public void clickEditProject() {
		click(editProjectBtn);
		sleep(2000);
	}

	@Step("Editing project's name to \"{name}\" via General Setting page")
	public void editProjectName(String name) {
		fillText(projectNameField, name);
		clickUpdateSettings();
		sleep(1000);
	}
}
