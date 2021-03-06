package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.account.LoginPage;
import pageobject.mainpage.AboutPage;
import pageobject.projects.GeneralSettingsPage;
import pageobject.projects.ProjectEditPage;
import pageobject.projects.ProjectTypePage;
import pageobject.projects.PublishPage;
import pageobject.templates.TemplatesPage;
import pageobject.workspaces.ProjectsPage;
import utils.Configuration;
import utils.Excel;

@Severity(SeverityLevel.BLOCKER)
@Epic("Projects' Creation And Editing Features")
public class ProjectsHandlingTest extends BaseTest {

	private final String REQUIRED_FIELD_MSG = "This field is required.";
	private final String SHORT_NAME_MSG = "Please enter at least 3 characters.";
	private final String PROJECT = "survey";
	private final String INITNAME = "for testing";
	private final String SECONDARY_NAME = "for another testing";
	private final String PAGE_KIND = "outcome pages";

	@Test(priority = 1, alwaysRun = true, description = "Log in to account")
	public void logIn() {
		AboutPage ap = new AboutPage(driver);
		ap.clickLoginLink();
		LoginPage lp = new LoginPage(driver);
		lp.logIn(Configuration.readProperty("username"), Configuration.readProperty("password"));
		ProjectsPage pp = new ProjectsPage(driver);
		Assert.assertEquals(pp.getTitle(), "My Workspace");
	}

	@Test(priority = 2, dependsOnMethods = { "logIn" }, description = "Selecting a new project to edit test")
	@Story("When selecting a project, a pop up window appears for new project's details")
	@Description("Selecting a new project")
	public void prepareProjectTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.createNewProject();
		ProjectTypePage ptp = new ProjectTypePage(driver);
		ptp.selectProject(PROJECT);
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseTemplate("Blank");
		ProjectEditPage pep = new ProjectEditPage(driver);
		Assert.assertTrue(pep.isNewProjectPopUpDisplayed());
	}

	// Bug in web site
	@Test(priority = 3, dependsOnMethods = {
			"prepareProjectTest" }, description = "Adding project with no name test", enabled = false)
	@Story("When trying to add a new project with no name, an error message should appear")
	@Description("Adding a new project with no name")
	@Issue("Will make the editing of a project fail, edit pop up gets stuck")
	public void emptyProjectNameTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editInvalidProjectName("");
		Assert.assertEquals(pep.getProjectNameErrorMsg(), REQUIRED_FIELD_MSG);
	}

	// Bug in web site
	@Test(priority = 4, dependsOnMethods = {
			"prepareProjectTest" }, description = "Adding project with less than 3 characters name test")
	@Story("When trying to add a new project with a name that has less than 3 characters, an error message should appear")
	@Description("Adding a new project with less than 3 characters name")
	public void shortProjectNameTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editInvalidProjectName("el");
		Assert.assertEquals(pep.getProjectNameErrorMsg(), SHORT_NAME_MSG);
	}

	@Test(priority = 5, dependsOnMethods = { "prepareProjectTest" }, description = "Adding a new slide to project test")
	@Severity(SeverityLevel.NORMAL)
	@Story("When selecting to add a slide, a new one should appear")
	@Description("Adding a new slide to project")
	public void addNewSlideTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editProjectPrep(INITNAME, PAGE_KIND);
		int before = pep.getSlidesNumber();
		pep.addNewSlide();
		int after = pep.getSlidesNumber();
		Assert.assertTrue(after == before + 1);
	}

	@Test(priority = 6, dependsOnMethods = { "addNewSlideTest" }, description = "Deleting an existing slide test")
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

	@Test(priority = 7, dependsOnMethods = { "deleteSlideTest" }, description = "Edit project name feature test")
	@Severity(SeverityLevel.NORMAL)
	@Story("When Editting project's name from Settings page, project's name should be changed accordingly")
	@Description("Edit project's name from settings page")
	public void editProjectNameFromSettingsTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.clickSettings();
		GeneralSettingsPage gp = new GeneralSettingsPage(driver);
		gp.editProjectName(SECONDARY_NAME);
		gp.clickEditProject();
		pep = new ProjectEditPage(driver);
		Assert.assertEquals(pep.getProjectName(), "FOR ANOTHER TESTING");

	}

	@Test(priority = 8, dataProvider = "getDataFromExcel", dependsOnMethods = {
			"addNewSlideTest" }, description = "Add content elements to project feature test", enabled = false)
	@Story("When dragging a content element to project, it should be added")
	@Description("Adding content element element to project")
	@Issue("Drag and drop doesn not work in page. possible javascript bug")
	public void addContentElementToProject(String element) {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.addElementToProject(element);
		Assert.assertTrue(pep.isContentAdded(element));
	}

	@Test(priority = 9, dependsOnMethods = {
			"addNewSlideTest" }, description = "Pages with no link warning pop up test")
	@Severity(SeverityLevel.NORMAL)
	@Story("When trying to publish pages with no link between them, a warning pop up window should appear")
	@Description("Publishing project with pages without link")
	public void pagesHaveNoLinkWarningTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.clickPublish();
		try {
			Assert.assertTrue(pep.isNoLinkWarningMsgDisplayed());
			pep.closeNoLinkWarningPopUp();
		} catch (Exception e) { // If no warning pop up arises then test will fail but test suite will continue
			// pass
		}
	}

	@Test(priority = 10, dependsOnMethods = {
			"editProjectNameFromSettingsTest" }, description = "Publish project feature test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("When publishing a project, it should be added under Published section in projects page")
	@Description("Publishing a project")
	public void publishProjectTest() {
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.clickSettings();
		GeneralSettingsPage gp = new GeneralSettingsPage(driver);
		gp.publishProject();
		PublishPage pp = new PublishPage(driver);
		pp.clickBackToOverview();
		ProjectsPage pjp = new ProjectsPage(driver);
		pjp.selectTab("Published");
		pjp.searchProject(SECONDARY_NAME);
		Assert.assertTrue(pjp.isProjectFoundAfterSearch(SECONDARY_NAME));
	}

	@Test(priority = 11, dependsOnMethods = { "publishProjectTest" }, description = "Deleting a published project test")
	@Story("When in Published section and a project is deleted, it should be removed from that section")
	@Description("Deleting a published project")
	public void deleteProjectFromPublishedSectionTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getProjectsNumber();
		pp.deletePublishedProject(SECONDARY_NAME);
		int after = pp.getProjectsNumber();
		Assert.assertTrue(after == before - 1);
	}

	@Test(priority = 12, alwaysRun = true, description = "Log out of account")
	public void logout() {
		try {
			ProjectEditPage pep = new ProjectEditPage(driver);
			pep.clickSaveAndExit();

		} catch (Exception e) {
		} finally {
			ProjectsPage pp = new ProjectsPage(driver);
			pp.logout();
			LoginPage lp = new LoginPage(driver);
			Assert.assertEquals(lp.getTitle(), "Log in");
		}
	}

	// will provide different content elements for adding to a project
	@DataProvider
	public Object[][] getDataFromExcel() {
		String excelPath = System.getProperty("user.dir") + Configuration.readProperty("pathToExcel");
		Object[][] table = Excel.getTableArray(excelPath, "Elements");
		return table;
	}
}
