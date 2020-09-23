package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
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

@Severity(SeverityLevel.NORMAL)
@Epic("WorkSpaces Creation and Editing Functionality")
public class WorkspacesTest extends BaseTest {

	private String projectName = "for testing";
	private String finalSlideType = "Thank you page";
	private String loginPageTitle = "Log in";
	private String projectType = "quiz";
	private String templateType = "Blank";
	private String workspaceName = "elias";
	private String newName = "elias_test";
	private String currentWorkspace = "My Workspace";
	private String noProjectFoundMsg = "No project matches the criteria";

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
		pp.createWorkSpace(workspaceName);
		int after = pp.getWorkspacesNumber();
		Assert.assertTrue(after == before + 1);
	}

	@Test(priority = 3, dependsOnMethods = { "createNewWorkspaceTest" })
	@Story("When renaimg an existing workspace, then the name should be changed")
	@Description("Renaming an existing workspace")
	public void renameWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.renameWorkSpace(workspaceName, newName);
		Assert.assertTrue(pp.isWorkSpaceFound(newName));
	}

	@Test(priority = 4, dependsOnMethods = { "renameWorkspaceTest" }, description = "Workspaces deletion feature test")
	@Story("When deleting a workspace, it should be removed from workspaces block")
	@Description("Deleting an existing workspace")
	public void deleteWorkspaceTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getWorkspacesNumber();
		pp.deleteWorkspace(newName);
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
		int numberFromWorkspacesBlock = pp.getProjectsNumberFromWorkspacesBlock(currentWorkspace);
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
		ptp.selectProject(projectType);
		TemplatesPage tp = new TemplatesPage(driver);
		tp.chooseTemplate(templateType);
		ProjectEditPage pep = new ProjectEditPage(driver);
		pep.editProjectPrep(projectName, finalSlideType);
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
		pp.searchProject(projectName);
		Assert.assertTrue(pp.isProjectFoundAfterSearch(projectName));
		pp.clickSearchBtn();
	}

	@Test(priority = 8, dependsOnMethods = { "logIn" }, description = "Search feature test")
	@Story("When searching for a non existing project, a proper message should appear")
	@Description("Search for a non existing project")
	public void searchForNonExistingProjectTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.searchProject("Some project");
		Assert.assertEquals(pp.getNoProjectFoundMsg(), noProjectFoundMsg);
		pp.clickSearchBtn();
	}

	@Test(priority = 9, dependsOnMethods = {
			"addProjectToWorkspaceTest" }, description = "Cancelation of project deleting process")
	@Story("When choosing cancel delete option, project should not be removed from workspace")
	@Description("Cancel project deletion")
	public void projectDeletionCancelationTest() {
		ProjectsPage pp = new ProjectsPage(driver);
		int before = pp.getProjectsNumber();
		pp.cancelProjectDeletion(projectName);
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
		pp.deleteProject(projectName);
		int after = pp.getProjectsNumber();
		Assert.assertTrue(after == before - 1);
	}

	@Test(priority = 11, alwaysRun = true, description = "Logout from site")
	public void logout() {
		ProjectsPage pp = new ProjectsPage(driver);
		pp.logout();
		LoginPage lp = new LoginPage(driver);
		Assert.assertEquals(lp.getTitle(), loginPageTitle);
	}
}
