package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Create new account POM*/
public class CreateNewAccountPage extends BasePage {

	@FindBy(css = "#user-name")
	private WebElement nameField;
	@FindBy(css = "#organization-name")
	private WebElement organizationField;
	@FindBy(css = "[type=\"email\"]")
	private WebElement emailField;
	@FindBy(css = "#register-password")
	private WebElement passwordField;
	@FindBy(css = "[role=\"checkbox\"]")
	private WebElement notRobotCheckBox;
	@FindBy(css = "#terms")
	private WebElement termsCheckBox;
	@FindBy(css = ".btn-lg")
	private WebElement registerBtn;

	// constructor
	public CreateNewAccountPage(WebDriver driver) {
		super(driver);
	}

	@Step("Creating new account with name: {name}, organization: {org}, email address: {email} and password: {password}")
	public void createNewAccount(String name, String org, String email, String password) {
		fillText(nameField, name);
		fillText(organizationField, org);
		fillText(emailField, email);
		fillText(passwordField, password);
		click(notRobotCheckBox);
		click(termsCheckBox);
		click(registerBtn);
	}
}
