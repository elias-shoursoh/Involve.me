package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.AboutPage;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import utils.Configuration;
@Severity(SeverityLevel.CRITICAL)
public class ForgotPasswordTest extends BaseTest {

	private final String successMsg = "A reset link has been sent to the email address, if it has been used to register for an account.";
	private final String failMsg = "We can't find a user with that e-mail address.";

	@Test(priority = 1, description = "Frogot password feature test with a valid email address")
	@Story("When sending a password reset link to a valid email address, a success message should appear")
	@Description("Sending password reset link to a valid email address")
	public void validEmailTest() {
		AboutPage ap = new AboutPage(driver);
		ap.clickLoginLink();
		LoginPage lp = new LoginPage(driver);
		lp.clickForgotPassword();
		ForgotPasswordPage fp = new ForgotPasswordPage(driver);
		fp.sendPasswordResetLink(Configuration.readProperty("username"));
		Assert.assertEquals(fp.getSuccessMsg(), successMsg);
	}

	@Test(priority = 2, dependsOnMethods = {
			"validEmailTest" }, description = "Forgot Password feature test with invalid email address")
	@Story("When sending a password reset link to an invalid email address, a failure message should appear")
	@Description("Sending password reset link to an invalid email address")
	public void invalidEmailTest() {
		ForgotPasswordPage fp = new ForgotPasswordPage(driver);
		fp.sendPasswordResetLink("something@gmail.com");
		Assert.assertEquals(fp.getInvalidEmailAddMsg(), failMsg);
	}
}
