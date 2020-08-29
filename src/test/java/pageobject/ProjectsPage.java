package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Projects POM - Where projects are added and edited*/
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
	private WebElement confirmationBtn; // Rename and create and delete workspace buttons
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

	@FindBy(css = "#app .max-w-full div .mt-4 > .mt-8 > div")
	private List<WebElement> projectsBlocks;
	@FindBy(css = ".mt-6 a")
	private List<WebElement> workspacesBlocks;
	@FindBy(css = "h1 a")
	private List<WebElement> projectsTitles;
	@FindBy(css = ".flex-no-wrap a")
	private List<WebElement> tabs;

	// selectors for each project
//	private By projectTitle = By.cssSelector("h1 a");
//	private By projectDropDownBtns = By.cssSelector(".justify-right button svg");

	// Drop down menu buttons names for each project
//	private String deleteProject = "delete project";
//	private String moveToWorkSpace = "move to workspace";

	// constructor
	public ProjectsPage(WebDriver driver) {
		super(driver);
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
		click(searchBtn);
		fillText(searchField, name);
		submit(searchField);
	}

	@Step("Delete project {title} from workspace")
	public void deleteProject(String projectName) {
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
//		click(getProjectDropDownBtn(deleteProject, title));
		click(confirmDeleteProjectBtn);
		sleep(3000);
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
		// click(getProjectDropDownBtn(deleteProject, title));
		click(cancelProjectDeletionBtn);
	}

	@Step("Go to project's analytics page")
	public void clickAnalytics(String projectName) {
		List<WebElement> projects = projectsBlocks;
		for (WebElement project : projects) {
			if (getText(project).contains(projectName)) {
				// getting drop down arrow element after clicking it
				clickDropDownBtn(project);
				// clicking on Analytics button
				click(project.findElement(By.cssSelector(".justify-right li:nth-child(4) a")));
				break;
			}
		}
	}

	@Step("Select {tabName} tab")
	public void selectTab(String tabName) {
		for (WebElement tab : tabs) {
			if (getText(tab).equalsIgnoreCase(tabName)) {
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

	// returns the projects page title
	public String getTitle() {
		return getText(projectPageTitle);
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

	// clicks on a specific project drop down arrow and returns the element
	private void clickDropDownBtn(WebElement project) {
		WebElement dropDownBtn = project.findElement(By.cssSelector(".justify-right button svg"));
		click(dropDownBtn);
	}

//	// method that returns the requested button' element from the drop down menu for
//	// require
//	// project
//	private WebElement getProjectDropDownBtn(String BtnName, String projectName) {
//		List<WebElement> drpdwnBtns = getElemList(getElementFromListByText(projectsBlocks, projectTitle, projectName),
//				projectDropDownBtns);
//		return getElementFromListByText(drpdwnBtns, null, BtnName);
//	}
}
