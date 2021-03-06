package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.account.LoginPage;
import pageobject.mainpage.AboutPage;
import pageobject.projects.ProjectEditPage;
import pageobject.projects.ProjectTypePage;
import pageobject.templates.TemplatesPage;
import pageobject.workspaces.ProjectsPage;
import utils.Configuration;

@Severity(SeverityLevel.NORMAL)
@Epic("WorkSpaces Creation and Editing Functionality")
public class WorkspacesTest extends BaseTest {

	private String PROJECT_NAME = "for testing";
	private String FINAL_SLIDE_TYPE = "Thank you page";
	private String LOGIN_PAGE_TITLE = "Log in";
	private String PROJECT_TYPE = "quiz";
	private String TEMPLATE_TYPE = "Blank";
	private String WORKSPACE_NAME = "elias";
	private String NEW_NAME = "elias_test";
	private String CURRENT_WORKSPACE = "My Workspace";
	private String NO_PROJECT_FOUND_MSG = "No project matches the criteria";

	@Test(priority = 1, alwaysRun = true, description = "Log in to site")
	public void logIn() {
		AboutPage ap = new AboutPage(driver);
		ap.clickLoginLink();
		LoginPage lp = new LoginPage(driver);
		lp.logIn(Configuration.readProperty("username"), Configuration.readProperty("password"));
		ProjectsPage pp = new ProjectsPage(driver);
		Assert.assertEquals(pp.getTitle(), "My Workspace");
	}

	@Test(priority = 2, dependsOnMethods = { "logIn" }, description = "Workspaces creation feature test")
	@Severity(SeverityLevel.BLOCKER)
	@Story("When creating a new workspace, then a new one should be added to workspaces block")
	@Description("Creating a new workspace")
	public void createNewWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getWorkspacesNumber();
		pp.createWorkSpace(WORKSPACE_NAME);
		int after = pp.getWorkspacesNumber();
		Assert.assertTrue(after == before + 1);
	}

	@Test(priority = 3, dependsOnMethods = { "createNewWorkspaceTest" })
	@Story("When renaimg an existing workspace, then the name should be changed")
	@Description("Renaming an existing workspace")
	public void renameWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.renameWorkSpace(WORKSPACE_NAME, NEW_NAME);
		Assert.assertTrue(pp.isWorkSpaceFound(NEW_NAME));
	}

	@Test(priority = 4, dependsOnMethods = { "renameWorkspaceTest" }, description = "Workspaces deletion feature test")
	@Story("When deleting a workspace, it should be removed from workspaces block")
	@Description("Deleting an existing workspace")
	public void deleteWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getWorkspacesNumber();
		pp.deleteWorkspace(NEW_NAME);
		int after = pp.getWorkspacesNumber();
		Assert.assertTrue(after == before - 1);
	}

	@Test(priority = 5, dependsOnMethods = {
			"logIn" }, description = "Comparison between the number of projects seen and the number that is shown in workspaces block")
	@Story("Number of existing projects in page should be equal to the number shown in workspaces block")
	@Description("Comparison between the actual number of projects seen and the number shown in workspaces block")
	public void numberOfExistingPorjectsTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int numberFromProjectsBlock = pp.getProjectsNumber();
		int numberFromWorkspacesBlock = pp.getProjectsNumberFromWorkspacesBlock(CURRENT_WORKSPACE);
		Assert.assertTrue(numberFromProjectsBlock == numberFromWorkspacesBlock);
	}

	@Test(priority = 6, dependsOnMethods = { "logIn" }, description = "Add project to workspace test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("When selecting a new project and add it, then it should be added to workspace")
	@Description("Selecting and adding a project to workspace")
	public void addProjectToWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getProjectsNumber();
		pp.createNewProject();
		ProjectTypePage ptp = new ProjectTypePage(driver);
		ptp.selectProject(PROJECT_TYPE);
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseTemplate(TEMPLATE_TYPE);
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editProjectPrep(PROJECT_NAME, FINAL_SLIDE_TYPE);
		pep.clickSaveAndExit();
		pp = new ProjectsPage(driver);
		int after = pp.getProjectsNumber();
		Assert.assertTrue(after == before + 1);
	}

	@Test(priority = 7, dependsOnMethods = { "addProjectToWorkspaceTest" }, description = "Search feature test")
	@Story("When searching for an existing project, at least one project with same name should be in view")
	@Description("Search for an existing project")
	public void searchProjectTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.searchProject(PROJECT_NAME);
		Assert.assertTrue(pp.isProjectFoundAfterSearch(PROJECT_NAME));
		pp.clickSearchBtn();
	}

	@Test(priority = 8, dependsOnMethods = { "logIn" }, description = "Search feature test")
	@Story("When searching for a non existing project, a proper message should appear")
	@Description("Search for a non existing project")
	public void searchForNonExistingProjectTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.searchProject("Some project");
		Assert.assertEquals(pp.getNoProjectFoundMsg(), NO_PROJECT_FOUND_MSG);
		pp.clickSearchBtn();
	}

	@Test(priority = 9, dependsOnMethods = {
			"addProjectToWorkspaceTest" }, description = "Cancelation of project deleting process")
	@Story("When choosing cancel delete option, project should not be removed from workspace")
	@Description("Cancel project deletion")
	public void projectDeletionCancelationTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getProjectsNumber();
		pp.cancelProjectDeletion(PROJECT_NAME);
		int after = pp.getProjectsNumber();
		Assert.assertTrue(before == after);
	}

	@Test(priority = 10, dependsOnMethods = {
			"addProjectToWorkspaceTest" }, description = "Delete project from workspace test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("When selecting a project to delete it, it should be removed from workspace")
	@Description("Deleting an existing project from workspace")
	public void deleteProjectFromWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getProjectsNumber();
		pp.deleteProject(PROJECT_NAME);
		int after = pp.getProjectsNumber();
		Assert.assertTrue(after == before - 1);
	}

	@Test(priority = 11, alwaysRun = true, description = "Logout from site")
	public void logout() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.logout();
		LoginPage lp = new LoginPage(driver);
		Assert.assertEquals(lp.getTitle(), LOGIN_PAGE_TITLE);
	}
}
