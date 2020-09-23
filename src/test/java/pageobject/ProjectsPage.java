package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Projects Page - Where projects are added and edited*/
public class ProjectsPage extends TopNavigateBar {

	@FindBy(css = "#app .px-4 a")
	private WebElement startBtn;
	@FindBy(css = ".font-medium button")
	private WebElement createNewWorkspaceBtn;
	@FindBy(css = "[data-icon=\"chevron-down\"]")
	private WebElement workspaceEditBtn;
	@FindBy(css = ".mr-3 .hover\\:bg-gray-600")
	private WebElement renameWorkspaceBtn;
	@FindBy(css = ".mr-3 .text-red-600")
	private WebElement deleteWorkspaceBtn;
	@FindBy(css = ".vue-portal-target input")
	private WebElement renameField;
	@FindBy(css = "#confirm-create-button")
	private WebElement confirmationBtn; // Rename , create, and delete workspace buttons
	@FindBy(css = "[placeholder=\"Workspace name\"]")
	private WebElement newWorkspaceNameField;
	@FindBy(css = ".h-12")
	private WebElement deleteWorkspaceField;
	@FindBy(css = ".hidden.px-3")
	private WebElement createProjectBtn; // Appears when there is at least one project added
	@FindBy(css = ".form-select")
	private WebElement sortBtn;
	@FindBy(css = "[data-icon=\"search\"]")
	private WebElement searchBtn;
	@FindBy(css = "[type=\"text\"]")
	private WebElement searchField;
	@FindBy(css = "#confirm-delete-button")
	private WebElement confirmDeleteProjectBtn; // Project deletion button
	@FindBy(css = "form [type=\"button\"]")
	private WebElement cancelProjectDeletionBtn; // Project cancel deletion button
	@FindBy(css = ".e-save-succes")
	private WebElement noLinkWarningPopUp;
	@FindBy(css = ".swal-button--cancelCustom")
	private WebElement closeWarningPopUpBtn;
	@FindBy(css = "#app h1.leading-tight.truncate")
	private WebElement projectPageTitle;
	@FindBy(css = "#app h1.block")
	private WebElement noProjectFoundMsg;

	@FindBy(css = "#app .max-w-full div .mt-4 > .mt-8 > div")
	private List<WebElement> projectsBlocks;
	@FindBy(css = ".mt-6 a")
	private List<WebElement> workspacesBlocks;
	@FindBy(css = "h1 a")
	private List<WebElement> projectsTitles;
	@FindBy(css = ".flex-no-wrap a")
	private List<WebElement> tabs;

	// constructor
	public ProjectsPage(WebDriver driver) {
		super(driver);
	}

	public void clickSearchBtn() {
		click(searchBtn);
	}

	@Step("Start a new project")
	public void createNewProject() {
		// in case there were no projects
		if (isElementDisplayed(startBtn)) {
			click(startBtn);
			// in case there was at least 1 project
		} else if (isElementDisplayed(createProjectBtn)) {
			click(createProjectBtn);
		}
	}

	@Step("Create new workspace {name}")
	public void createWorkSpace(String name) {
		click(createNewWorkspaceBtn);
		fillText(newWorkspaceNameField, name);
		click(confirmationBtn);
		sleep(1000);
	}

	@Step("Renaming workspace {oldName} to: {newName}")
	public void renameWorkSpace(String oldName, String newName) {
		List<WebElement> workspaces = workspacesBlocks;
		for (WebElement workspace : workspaces) {
			if (getText(workspace).contains(oldName)) {
				click(workspace);
				click(workspaceEditBtn);
				click(renameWorkspaceBtn);
				fillText(renameField, newName);
				click(confirmationBtn);
				sleep(1000);
				break;
			}
		}
	}

	@Step("Deleting workspace: {name}")
	public void deleteWorkspace(String name) {
		List<WebElement> workspaces = workspacesBlocks;
		for (WebElement workspace : workspaces) {
			if (getText(workspace).contains(name)) {
				click(workspace);
				click(workspaceEditBtn);
				click(deleteWorkspaceBtn);
				fillText(deleteWorkspaceField, name);
				click(confirmationBtn);
				sleep(1000);
				break;
			}
		}
	}

	@Step("Search for project {name}")
	public void searchProject(String name) {
		sleep(1000);
		clickSearchBtn();
		fillText(searchField, name);
	}

	@Step("Delete project {projectName} from workspace")
	public void deleteProject(String projectName) {
		List<WebElement> projects = projectsBlocks;
		// counter for the maximum number of iterations to wait for the project to be
		// deleted/removed from page
		long counter = 0;
		// will be used for waiting until project is deleted before finishing this
		// method
		int projectsListSize = projects.size();
		for (WebElement project : projects) {
			if (getText(project).contains(projectName)) {
				// clicking drop down arrow
				clickDropDownBtn(project);
				// clicking on delete project button
				click(project.findElement(By.cssSelector(".dropdown-menu li:nth-child(8)")));
				break;
			}
		}
		click(confirmDeleteProjectBtn);
		projects = projectsBlocks;
		// while list size is still the same and counter < 2000 continue waiting
		while (projectsListSize == projects.size() && counter < 2000) {
			projects = projectsBlocks;
			counter++;
		}
	}

	@Step("Delete project {projectName} from Published section in workspace")
	public void deletePublishedProject(String projectName) {
		List<WebElement> projects = projectsBlocks;
		// counter for the maximum number of iterations to wait for the project to be
		// deleted/removed from page
		long counter = 0;
		// will be used for waiting until project is deleted before finishing this
		// method
		int projectsListSize = projects.size();
		for (WebElement project : projects) {
			if (getText(project).contains(projectName)) {
				// clicking drop down arrow
				clickDropDownBtn(project);
				// clicking on delete project button
				click(project.findElement(By.cssSelector(".max-w-full li:nth-child(11) button")));
				break;
			}
		}
		click(confirmDeleteProjectBtn);
		projects = projectsBlocks;
		// while list size is still the same and counter < 2000 continue waiting
		while (projectsListSize == projects.size() && counter < 2000) {
			projects = projectsBlocks;
			counter++;
		}
	}

	@Step("Cancel project deletion")
	public void cancelProjectDeletion(String projectName) {
		List<WebElement> projects = projectsBlocks;
		for (WebElement project : projects) {
			if (getText(project).contains(projectName)) {
				// getting drop down arrow element after clicking it
				clickDropDownBtn(project);
				// clicking on delete project button
				click(project.findElement(By.cssSelector(".dropdown-menu li:nth-child(8)")));
				break;
			}
		}
		click(cancelProjectDeletionBtn);
	}

	@Step("Go to {projectName}'s preview page")
	public void clickAnalytics(String projectName) {
		List<WebElement> projects = projectsBlocks;
		for (WebElement project : projects) {
			if (getText(project).contains(projectName)) {
				// getting drop down arrow element after clicking it
				clickDropDownBtn(project);
				// clicking on Analytics button
				click(project.findElement(By.cssSelector(".justify-right li:nth-child(2) a")));
				break;
			}
		}
	}

	@Step("Select {tabName} tab")
	public void selectTab(String tabName) {
		for (WebElement tab : tabs) {
			if (getText(tab).contains(tabName)) {
				click(tab);
				break;
			}
		}
	}

	@Step("Get number of existing workspaces")
	public int getWorkspacesNumber() {
		List<WebElement> workspaces = workspacesBlocks;
		return workspaces.size();
	}

	@Step("Get number of existing projects")
	public int getProjectsNumber() {
		List<WebElement> projects = projectsBlocks;
		return projects.size();
	}

	@Step("Get number of existing project from workspaces block")
	public int getProjectsNumberFromWorkspacesBlock(String workspaceName) {
		List<WebElement> workspaces = workspacesBlocks;
		WebElement number = null;
		for (WebElement workspace : workspaces) {
			if (getText(workspace).contains(workspaceName)) {
				// get the element of the number shown next to workspace name
				number = workspace.findElement(By.cssSelector("span:nth-child(2)"));
				break;
			}
		}
		return Integer.parseInt(getText(number));
	}

	// returns the projects page title
	public String getTitle() {
		return getText(projectPageTitle);
	}

	// returns the no project found message after search
	public String getNoProjectFoundMsg() {
		return getText(noProjectFoundMsg);
	}

	// checks if a specific workspace is in workspaces block
	public boolean isWorkSpaceFound(String workspaceName) {
		List<WebElement> workspaces = workspacesBlocks;
		for (WebElement workspace : workspaces) {
			if (getText(workspace).contains(workspaceName)) {
				return true;
			}
		}
		return false;
	}

	// checks if all projects in view are with the same name (this is used after
	// search functionality)
	public boolean isProjectFoundAfterSearch(String project) {
		sleep(1000);
		List<WebElement> projectsNames = projectsTitles;
		for (WebElement name : projectsNames) {
			if (!getText(name).equalsIgnoreCase(project)) {
				return false;
			}
		}
		return true;
	}

	// clicks on a specific project's drop down arrow and returns the element
	private void clickDropDownBtn(WebElement project) {
		WebElement dropDownBtn = project.findElement(By.cssSelector(".justify-right button svg"));
		click(dropDownBtn);
	}
}
