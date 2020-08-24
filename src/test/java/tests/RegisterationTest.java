package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.CreateNewAccountPage;
import pageobject.LoginPage;
import utils.Configuration;

public class RegisterationTest extends BaseTest {

	private final String name = "Mathew Wallace";
	private final String existingEmailMsg = "The email has already been taken.";
	private final String captchaErrorMsg = "The captcha field is required.";
	private final String validEmail = "eliassh@live.com";

	@Test(priority = 1, description = "Registeration feature test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("When registering with valid credentials but with an exisitng email address, an error message should appear")
	@Description("Creating new account with valid input but with an existing email address")
	public void createNewAccountWithExistingEmailTest() {
		LoginPage lp = new LoginPage(driver);
		lp.clickLogin();
		lp.clickCreateAnAccount();
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccount(name, Configuration.readProperty("username"));
		Assert.assertTrue((cp.getFailureMsg().contains(existingEmailMsg)));
	}

	@Test(priority = 2, description = "Registeratrion feature without captcha test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("When registering with valid input but do not check the I'm not a robot check box, then an error message should appear")
	@Description("Creating new account with valid input but without captcha")
	public void createNewAccountWithouCaptchaTest() {
		LoginPage lp = new LoginPage(driver);
		lp.clickLogin();
		lp.clickCreateAnAccount();
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccount(name, validEmail);
		Assert.assertTrue((cp.getFailureMsg()).contains(captchaErrorMsg));
	}
}
