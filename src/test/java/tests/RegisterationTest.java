package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.AboutPage;
import pageobject.CreateNewAccountPage;
import utils.Configuration;
import utils.Excel;

@Severity(SeverityLevel.CRITICAL)
public class RegisterationTest extends BaseTest {

	private final String name = "Mathew Wallace";
	private final String existingEmailMsg = "The email has already been taken.";
	private final String captchaErrorMsg = "The captcha field is required.";
	private final String invalidEmailMsg = "Please enter a valid email address.";
	private final String shortPassMsg = "Please enter at least 10 characters.";
	private final String invalidPassMsg = "The password must contain a minimum of one lower case character, one upper case character and one digit.";
	private final String requiredFieldMsg = "This field is required.";
	private final String validEmail = "eliassh@live.com";
	private final String invalidEmail = "elias";
	private final String shortPass = "jkl";

	@Test(priority = 1, description = "Registeration feature test", enabled = false)
	@Story("When registering with valid credentials but with an exisitng email address, an error message should appear")
	@Description("Creating new account with valid input but with an existing email address")
	public void createNewAccountWithExistingEmailTest() {
		AboutPage ap = new AboutPage(driver);
		ap.clickRegisterLink();
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccount(name, Configuration.readProperty("username"));
		Assert.assertTrue((cp.getFailureMsg().contains(existingEmailMsg)));
	}

	@Test(priority = 2, description = "Registeratrion feature without captcha test")
	@Story("When registering with valid input but do not check the I'm not a robot check box, then an error message should appear")
	@Description("Creating new account with valid input but without captcha")
	public void createNewAccountWithouCaptchaTest() {
		try {
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) {
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccount(name, validEmail);
		Assert.assertTrue((cp.getFailureMsg()).contains(captchaErrorMsg));
	}

	@Test(priority = 3, description = "Registeration feature with invalid email test")
	@Story("When registering with an invalid email, an error message should appear")
	@Description("Creating new account with invalid email")
	public void createNewAccountWithInvalidEmailTest() {
		try {
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) {
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidEmail(name, invalidEmail);
		Assert.assertEquals(cp.getInvalidEmailMsg(), invalidEmailMsg);
	}

	@Test(priority = 4, description = "Registeration feature with short password test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When registering with a short password than requried, an error message should appear")
	@Description("Creating new account with short password")
	public void createNewAccountWithShortPasswordTest() {
		try {
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) {
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidPassword(name, validEmail, shortPass);
		Assert.assertEquals(cp.getInvalidPasswordMsg(), shortPassMsg);
	}

	@Test(priority = 5, dataProvider = "getDataFromExcel", description = "Registeration feature with invalid password test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When registering with an invalid password, an error message should appear")
	@Description("Creating new account with invalid password")
	public void createNewAccountWithInvalidPasswordTest(String passowrd) {
		try { // the first test in this series of tests will enter the try block since the
				// first page after browsing is the about page
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) { // the rest of the tests will enter the catch block since now it will be in new
								// account page
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidPassword(name, validEmail, passowrd);
		Assert.assertEquals(cp.getInvalidPasswordMsg(), invalidPassMsg);
	}

	@Test(priority = 6, description = "Registeration feature with empty email field test")
	@Story("When registering with empty email field, a required field message should appear")
	@Description("Creating new account with empty email")
	public void createNewAccountWithEmptyEmailTest() {
		try {
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) {
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidEmail(name, "");
		Assert.assertEquals(cp.getInvalidEmailMsg(), requiredFieldMsg);
	}

	@Test(priority = 7, description = "Registeration feature with empty password field test")
	@Story("When registering with empty password field, a required field message should appear")
	@Description("Creating new account with empty password")
	public void createNewAccountWithEmptyPasswordTest() {
		try { // the first test in this series of tests will enter the try block since the
				// first page after browsing is the about page
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) { // the rest of the tests will enter the catch block since now it will be in new
								// account page
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidPassword(name, validEmail, "");
		Assert.assertEquals(cp.getInvalidPasswordMsg(), requiredFieldMsg);
	}

	// will provide different variation of invalid passwords
	@DataProvider
	public Object[][] getDataFromExcel() {
		String excelPath = System.getProperty("user.dir") + Configuration.readProperty("pathToExcel");
		Object[][] table = Excel.getTableArray(excelPath, "Passwords");
		return table;
	}
}
