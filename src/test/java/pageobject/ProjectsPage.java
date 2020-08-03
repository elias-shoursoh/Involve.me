package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Projects POM - Where projects are added and edited*/
public class ProjectsPage extends BasePage {

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
	@FindBy(css = "[placeholder=\"My Workspace\"]")
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
	@FindBy(css = ".md\\:flex-wrap > div")
	private List<WebElement> projectsBlocks;
	@FindBy(css = ".mt-6 a")
	private List<WebElement> workspacesBlocks;

	// selectors for each project
	private By projectTitle = By.cssSelector("h1 a");
	private By projectDropDownBtns = By.cssSelector(".dropdown-menu button");

	// Drop down menu buttons names for each project
	private String deleteProject = "delete project";
	private String moveToWorkSpace = "move to workspace";

	// constructor
	public ProjectsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click Start button")
	public void clickStart() {
		click(startBtn);
	}

	@Step("Create new workspace {name}")
	public void createWorkSpace(String name) {
		click(createNewWorkspaceBtn);
		fillText(newWorkspaceNameField, name);
		click(confirmationBtn);
	}

	@Step("Renaming workspace {oldName} to: {newName}")
	public void renameWorkSpace(String oldName, String newName) {
		for (WebElement workspace : workspacesBlocks) {
			if (getText(workspace).equals(oldName)) {
				click(workspace);
				click(workspaceEditBtn);
				click(renameWorkspaceBtn);
				fillText(renameField, newName);
				click(confirmationBtn);
			}
		}
	}

	@Step("Deleting workspace: {name}")
	public void deleteWorkspace(String name) {
		for (WebElement workspace : workspacesBlocks) {
			if (getText(workspace).equals(name)) {
				click(workspace);
				click(workspaceEditBtn);
				click(deleteWorkspaceBtn);
				fillText(deleteWorkspaceBtn, name);
				click(confirmationBtn);
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
	public void deleteProject(String title) {
		click(getProjectDropDownBtn(deleteProject, title));
		click(confirmDeleteProjectBtn);
	}

	@Step("Cancel project deletion")
	public void cancelProjectDeletion(String title) {
		click(getProjectDropDownBtn(deleteProject, title));
		click(cancelProjectDeletionBtn);
	}

	@Step("Get number of existing workspaces")
	public int getWorkspacesNumber() {
		return workspacesBlocks.size();
	}

	@Step("Get number of existing projects")
	public int getProjectsNumber() {
		return projectsBlocks.size();
	}

	// method that returns the requested button' element from the drop down menu for require
	// project
	private WebElement getProjectDropDownBtn(String BtnName, String projectName) {
		List<WebElement> drpdwnBtns = getElemList(getElementFromListByText(projectsBlocks, projectTitle, projectName),
				projectDropDownBtns);
		return getElementFromListByText(drpdwnBtns, null, BtnName);
	}
}
