package pageobject;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Create new account POM*/
public class CreateNewAccountPage extends TopMenuBar {

	@FindBy(css = "#user-name")
	private WebElement nameField;
	@FindBy(css = "#organization-name")
	private WebElement organizationField;
	@FindBy(css = "[type=\"email\"]")
	private WebElement emailField;
	@FindBy(css = "#register-password")
	private WebElement passwordField;
	@FindBy(css = "#recaptcha-anchor")
	private WebElement notRobotCheckBox;
	@FindBy(css = "iframe[role=\"presentation\"]")
	private WebElement notRobotFrame;
	@FindBy(css = "#terms")
	private WebElement termsCheckBox;
	@FindBy(css = ".btn-lg")
	private WebElement registerBtn;
	@FindBy(css = "#info-url")
	private WebElement availability;
	@FindBy(css = ".alert-danger")
	private WebElement failureMsg;
	@FindBy(css = "#email-error")
	private WebElement invalidEmailMsg;

	private final String notAvailabe = "(not available)";
	Random rand;

	// constructor
	public CreateNewAccountPage(WebDriver driver) {
		super(driver);
		rand = new Random();
	}

	@Step("Creating new account with name: {name} and email address: {email}")
	public void createNewAccount(String name, String email) {
		sleep(3000);
		fillText(nameField, name);
		// concatenates "Automation" string to a random number between 0 to 999
		fillText(organizationField, "Automation" + String.valueOf(rand.nextInt(1000)));
		clearTextBox(organizationField);
		fillText(organizationField, "Automation" + String.valueOf(rand.nextInt(1000)));
		// if this organization exists, then enter another organization
		while (isNotAvailable()) {
			fillText(organizationField, "Automation" + String.valueOf(rand.nextInt(1000)));
		}
		fillText(emailField, email);
		fillText(passwordField, "Jkljkm1c123");
		moveToElement(termsCheckBox);
		click(termsCheckBox);
		click(registerBtn);
	}

	@Step("Get failure message")
	public String getFailureMsg() {
		return getText(failureMsg);
	}

	@Step("Get invalid email error message")
	public String getInvalidEmailMsg() {
		return getText(invalidEmailMsg);
	}

	// method that checks if an organization was already registered to site
	private boolean isNotAvailable() {
		return (getText(availability).equals(notAvailabe));
	}
}
