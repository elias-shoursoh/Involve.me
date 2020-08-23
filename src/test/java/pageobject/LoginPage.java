package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Login POM*/
public class LoginPage extends TopMenuBar {

	@FindBy(css = "[name=\"email\"]")
	private WebElement emailField;
	@FindBy(css = "[name=\"password\"]")
	private WebElement passwordField;
	@FindBy(css = ".btn.btn-lg")
	private WebElement loginBtn;
	@FindBy(css = "p .darken")
	private WebElement createAccountLink;
	@FindBy(css = "div:nth-child(6) a")
	private WebElement forgotPasswordLink;
	@FindBy(css = ".alert")
	private WebElement invalidCredsMsg;
	@FindBy(css = ".e-form-heading")
	private WebElement pageTitle;

	// constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Step("Login with user: {user} and password: {password}")
	public void logIn(String user, String password) {
		fillText(emailField, user);
		fillText(passwordField, password);
		click(loginBtn);
	}

	@Step("Click Create an account link button")
	public void clickCreateAnAccount() {
		click(createAccountLink);
	}

	@Step("Click Forgot password link button")
	public void clickForgotPassword() {
		click(forgotPasswordLink);
	}

	@Step("Get Inavlid credentials error message")
	public String getInvalidCredsMsg() {
		return getText(invalidCredsMsg);
	}

	@Step("Get log in page's title")
	public String getTitle() {
		return getText(pageTitle);
	}
}
