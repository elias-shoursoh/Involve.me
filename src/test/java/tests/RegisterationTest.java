package tests;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.CreateNewAccountPage;
import pageobject.FirstTimeLogInPage;
import pageobject.LoginPage;
import pageobject.ProjectsPage;

//TODO: try to get pass "I am not a robot" issue"

public class RegisterationTest extends BaseTest {

	private final String name = "Mathew Wallace";
	private final String emailPrefix = "eliassh";
	private final String addressPrefix = "@live.com";
	private final String pageTitle = "Workspaces";

	@Test(priority = 1, description = "Registeration feature test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("When registering with valid credentials and input, a new account should be created")
	@Description("Creating new account with valid input")
	public void createNewAccountTest() {
		LoginPage lp = new LoginPage(driver);
		lp.clickLogin();
		lp.clickCreateAnAccount();
		CreateNewAccountPage cp = new CreateNewAccountPage(driver);
		cp.createNewAccount(name, emailPrefix + getTimeStamp() + addressPrefix);
		FirstTimeLogInPage ftp = new FirstTimeLogInPage(driver);
		ftp.manageWelcomePage("quiz", "for fun", "no");
		ProjectsPage pp = new ProjectsPage(driver);
		Assert.assertEquals(pp.getTitle(), pageTitle);
	}

	@Test(priority = 2, description = "Log out")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Log out from site")
	public void logout() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.logout();
	}

	// gets current time stamp, removes spaces,: and -
	private String getTimeStamp() {
		return new Timestamp(System.currentTimeMillis()).toString().replaceAll(" ", "").replaceAll("-", "")
				.replaceAll(":", "");
	}
}
