package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.AboutPage;
import pageobject.LoginPage;
import pageobject.ProjectEditPage;
import pageobject.ProjectTypePage;
import pageobject.ProjectsPage;
import pageobject.TemplatesPage;
import utils.Configuration;

public class ProjectEdittingTest extends BaseTest {

	private final String requiredFieldMsg = "This field is required.";
	private final String shortNameMsg = "Please enter at least 3 characters.";

	@Test(priority = 1, description = "Log in to account")
	public void logIn() {
		AboutPage ap = new AboutPage(driver);
		ap.clickLoginLink();
		LoginPage lp = new LoginPage(driver);
		lp.logIn(Configuration.readProperty("username"), Configuration.readProperty("password"));
		Assert.assertEquals(lp.getTitle(), "My Workspace");
	}

	@Test(priority = 2, dependsOnMethods = { "LogIn" }, description = "Selecting a new project to edit test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When selecting a project, a pop up window appears for new project's details")
	@Description("Selecting a new project")
	public void prepareProjectTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.createNewProject();
		ProjectTypePage ptp = new ProjectTypePage(driver);
		ptp.selectProject("Survey");
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseTemplate("Blank");
		ProjectEditPage pep = new ProjectEditPage(driver);
		Assert.assertTrue(pep.isNewProjectPopUpDisplayed());
	}

	@Test(priority = 3, dependsOnMethods = { "prepareProjectTest" }, description = "Adding project with no name test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When trying to add a new project with no name, an error message should appear")
	@Description("Adding a new project with no name")
	public void emptyProjectNameTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editInvalidProjectName("");
		Assert.assertEquals(pep.getProjectNameErrorMsg(), requiredFieldMsg);
	}

	@Test(priority = 4, dependsOnMethods = {
			"prepareProjectTest" }, description = "Adding project with less than 3 characters name test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When trying to add a new project with a name that has less than 3 characters, an error message should appear")
	@Description("Adding a new project with less than 3 characters name")
	public void shortProjectNameTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editInvalidProjectName("el");
		Assert.assertEquals(pep.getProjectNameErrorMsg(), shortNameMsg);
	}

	@Test(priority = 5, dependsOnMethods = { "prepareProjectTest" }, description = "Adding a new slide to project test")
	@Severity(SeverityLevel.NORMAL)
	@Story("When selecting to add a slide, a new one should appear")
	@Description("Adding a new slide to project")
	public void addNewSlideTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editProjectPrep("for testing", "outcome pages");
		int before = pep.getSlidesNumber();
		pep.addNewSlide();
		int after = pep.getSlidesNumber();
		Assert.assertTrue(after == before + 1);
	}

	@Test(priority = 6, dependsOnMethods = { "prepareProjectTest" }, description = "Deleting an existing slide test")
	@Severity(SeverityLevel.NORMAL)
	@Story("When deleting an existing slide from project, slide should be removed")
	@Description("Deleting an existing slide from project")
	public void deleteSlideTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		int before = pep.getSlidesNumber();
		pep.deleteSlide(2);
		int after = pep.getSlidesNumber();
		Assert.assertTrue(after == before - 1);
	}

	@Test(priority = 7, description = "Log out of account")
	public void logout() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.logout();
		LoginPage lp = new LoginPage(driver);
		Assert.assertEquals(lp.getTitle(), "Log in");
	}
}
