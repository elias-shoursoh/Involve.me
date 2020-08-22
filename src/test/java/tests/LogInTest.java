package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.LoginPage;
import pageobject.ProjectsPage;
import utils.Configuration;

public class LogInTest extends BaseTest {

	private final String title = "Workspaces";

	@Test(priority = 1, description = "Valid Log in")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When logging with a valid username and password project page should appear")
	@Description("Log in with a valid username and a valid password")
	public void validLogIn() {
		LoginPage lp = new LoginPage(driver);
		lp.clickLogin();
		lp.logIn(Configuration.readProperty("username"), Configuration.readProperty("password"));
		ProjectsPage pp = new ProjectsPage(driver);
		Assert.assertEquals(pp.getTitle(), title);
	}
}
