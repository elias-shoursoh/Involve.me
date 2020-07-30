package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

/*Projects POM*/
public class ProjectsPage extends BasePage {

	@FindBy(css = "#app .px-4 a")
	private WebElement startBtn;
	@FindBy(css = ".font-medium button")
	private WebElement createNewWorkspaceBtn;
	@FindBy(css = "[data-icon=\"chevron-down\"]")
	private WebElement workspaceEditBtn;
	@FindBy(css = ".dropdown.mr-3 .hover\\:bg-gray-600")
	private WebElement renameWorkspaceBtn;
	@FindBy(css = ".dropdown.mr-3 .text-red-600")
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

	// Will be used to find webelement list of all existing workspaces
	String workspacesMenuSelector = ".mt-6 a";

	// TODO: maybe this should be transfered to a FindBy object; this will get me
	// the text part in each project
	String projectsListSelector = ".md\\:flex-wrap .mb-3";
	// Will be used to find webelement list of all existing projects
	String projectsInListSelector = ".md\\:flex-wrap";

	// constructor
	public ProjectsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click Start button")
	public void clickStart() {
		click(startBtn);
	}

	public void createWorkSpace(String name) {
		click(createNewWorkspaceBtn);
		fillText(newWorkspaceNameField, name);
		click(confirmationBtn);
	}

	@Step("Renaming workspace {oldName} to: {newName}")
	public void renameWorkSpace(String oldName, String newName) {
		List<WebElement> workspaces = findElemList(workspacesMenuSelector);
		for (WebElement workspace : workspaces) {
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
		List<WebElement> workspaces = findElemList(workspacesMenuSelector);
		for (WebElement workspace : workspaces) {
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

	@Step("Get number of existing workspaces")
	public int getWorkspacesNumber() {
		List<WebElement> workspaces = findElemList(workspacesMenuSelector);
		return workspaces.size();
	}

	@Step("Get number of existing projects")
	public int getProjectsNumber() {
		List<WebElement> projects = findElemList(projectsInListSelector);
		return projects.size();
	}
}
