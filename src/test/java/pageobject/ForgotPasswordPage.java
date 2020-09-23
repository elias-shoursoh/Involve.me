package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/* Forgot password page */
public class ForgotPasswordPage extends BasePage {

	@FindBy(css = "[name=\"email\"]")
	private WebElement emailAddField;
	@FindBy(css = "[type=\"submit\"]")
	private WebElement sendPassResetBtn;
	@FindBy(css = ".alert-danger")
	private WebElement invalidUserMsg;
	@FindBy(css = ".alert-success")
	private WebElement successMsg;

	// constructor
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}

	@Step("Sending password reset link to email address: {email}")
	public void sendPasswordResetLink(String email) {
		fillText(emailAddField, email);
		click(sendPassResetBtn);
	}

	@Step("Get invalid email address message")
	public String getInvalidEmailAddMsg() {
		return getText(invalidUserMsg);
	}

	@Step("Get success message after reset link is sent")
	public String getSuccessMsg() {
		return getText(successMsg);
	}
}
