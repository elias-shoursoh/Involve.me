package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.account.CreateNewAccountPage;
import pageobject.mainpage.AboutPage;
import utils.Configuration;
import utils.Excel;

@Severity(SeverityLevel.CRITICAL)
@Epic("Creation Of A New Account Functionality")
public class RegisterationTest extends BaseTest {

	private final String NAME = "Mathew Wallace";
	private final String EXISTING_EMAIL_MSG = "The email has already been taken.";
	private final String CAPTCHA_ERROR_MSG = "The captcha field is required.";
	private final String INVALID_EMAIL_MSG = "Please enter a valid email address.";
	private final String SHORT_PASS_MSG = "Please enter at least 10 characters.";
	private final String INVALID_PASS_MSG = "The password must contain a minimum of one lower case character, one upper case character and one digit.";
	private final String REQUIRED_FIELD_MSG = "This field is required.";
	private final String VALID_EMAIL = "eliassh@live.com";
	private final String INVALID_EMAIL = "elias";
	private final String SHORT_PASS = "jkl";

	@Test(priority = 1, description = "Registeration feature test")
	@Story("When registering with valid credentials but with an exisitng email address, an error message should appear")
	@Description("Creating new account with valid input but with an existing email address")
	public void createNewAccountWithExistingEmailTest() {
		AboutPage ap = new AboutPage(driver);
		ap.clickRegisterLink();
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccount(NAME, Configuration.readProperty("username"));
		Assert.assertTrue((cp.getFailureMsg().contains(EXISTING_EMAIL_MSG)));
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
		cp.createNewAccount(NAME, VALID_EMAIL);
		Assert.assertTrue((cp.getFailureMsg()).contains(CAPTCHA_ERROR_MSG));
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
		cp.createNewAccountWithInvalidEmail(NAME, INVALID_EMAIL);
		Assert.assertEquals(cp.getInvalidEmailMsg(), INVALID_EMAIL_MSG);
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
		cp.createNewAccountWithInvalidPassword(NAME, VALID_EMAIL, SHORT_PASS);
		Assert.assertEquals(cp.getInvalidPasswordMsg(), SHORT_PASS_MSG);
	}

	@Test(priority = 5, dataProvider = "getDataFromExcel", description = "Registeration feature with invalid password test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When registering with an invalid password, an error message should appear")
	@Description("Creating new account with invalid password")
	public void createNewAccountWithInvalidPasswordTest(String passowrd) {
		try { // the first test in this series of tests will enter the try block since the
				// first page after browsing is the About page
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) { // the rest of the tests will enter the catch block since now it will be in New
								// Account page
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidPassword(NAME, VALID_EMAIL, passowrd);
		Assert.assertEquals(cp.getInvalidPasswordMsg(), INVALID_PASS_MSG);
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
		cp.createNewAccountWithInvalidEmail(NAME, "");
		Assert.assertEquals(cp.getInvalidEmailMsg(), REQUIRED_FIELD_MSG);
	}

	@Test(priority = 7, description = "Registeration feature with empty password field test")
	@Story("When registering with empty password field, a required field message should appear")
	@Description("Creating new account with empty password")
	public void createNewAccountWithEmptyPasswordTest() {
		try { // the first test in this series of tests will enter the try block since the
				// first page after browsing is the About page
			AboutPage ap = new AboutPage(driver);
			ap.clickRegisterLink();
		} catch (Exception e) { // the rest of the tests will enter the catch block since now it will be in New
								// Account page
			CreateNewAccountPage cp = new CreateNewAccountPage(driver);
			cp.clickRegister();
		}
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccountWithInvalidPassword(NAME, VALID_EMAIL, "");
		Assert.assertEquals(cp.getInvalidPasswordMsg(), REQUIRED_FIELD_MSG);
	}

	// will provide different variation of invalid passwords
	@DataProvider
	public Object[][] getDataFromExcel() {
		String excelPath = System.getProperty("user.dir") + Configuration.readProperty("pathToExcel");
		Object[][] table = Excel.getTableArray(excelPath, "Passwords");
		return table;
	}
}
