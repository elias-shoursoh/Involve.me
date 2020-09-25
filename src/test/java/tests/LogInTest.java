package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.account.LoginPage;
import pageobject.mainpage.AboutPage;
import pageobject.workspaces.ProjectsPage;
import utils.Configuration;
import utils.Excel;

@Severity(SeverityLevel.BLOCKER)
@Epic("Login Feature's Functionality")
public class LogInTest extends BaseTest {

	private final String title = "My Workspace";
	private final String invalidLogInMsg = "These credentials do not match our records.";
	private final String logInPageTitle = "Log in";

	@Test(priority = 1, description = "Valid log in")
	@Story("When logging with a valid username and password project page should appear")
	@Description("Log in with a valid username and a valid password")
	public void validLogInTest() {
		AboutPage ap = new AboutPage(driver);
		ap.clickLoginLink();
		LoginPage lp = new LoginPage(driver);
		lp.logIn(Configuration.readProperty("username"), Configuration.readProperty("password"));
		ProjectsPage pp = new ProjectsPage(driver);
		Assert.assertEquals(pp.getTitle(), title);
	}

	@Test(priority = 2, description = "Log out from site")
	@Story("When clicking on account name and then log out, account should be logged out")
	@Description("Logging out of account")
	public void logoutTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.logout();
		LoginPage lp = new LoginPage(driver);
		Assert.assertEquals(lp.getTitle(), logInPageTitle);
	}

	@Test(priority = 3, dataProvider = "getDataFromExcel", description = "Invalid log in")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When logging in with invalid email or invalid password or both invalids, an error message should appear")
	@Description("Log in with various emails and passwords")
	public void invalidLogInTest(String username, String password) {
		LoginPage lp = new LoginPage(driver);
		// the below is commented out because it is only used if need be to debug this
		// test case
		// lp.clickLogin();
		lp.logIn(username, password);
		Assert.assertEquals(lp.getInvalidCredsMsg(), invalidLogInMsg);
	}

	@DataProvider
	public Object[][] getDataFromExcel() {
		String excelPath = System.getProperty("user.dir") + Configuration.readProperty("pathToExcel");
		Object[][] table = Excel.getTableArray(excelPath, "Credentials");
		return table;
	}
}
