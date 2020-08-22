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
	@FindBy(css = ".e-save-succes")
	private WebElement noLinkWarningPopUp;
	@FindBy(css = ".swal-button--cancelCustom")
	private WebElement closeWarningPopUpBtn;
	@FindBy(css = ".container .flex.text-lg span")
	private WebElement projectPageTitle;

	@FindBy(css = ".md\\:flex-wrap > div")
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
				break;
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
		for (WebElement project : projectsBlocks) {
			if (getText(project).contains(projectName)) {
				// getting drop down arrow element after clicking it
				WebElement dropDownBtn = clickDropDownBtn(project);
				// clicking on delete project button
				click(dropDownBtn.findElement(By.cssSelector(".justify-right li:nth-child(9) button")));
				break;
			}
		}
//		click(getProjectDropDownBtn(deleteProject, title));
		click(confirmDeleteProjectBtn);
	}

	@Step("Cancel project deletion")
	public void cancelProjectDeletion(String projectName) {
		for (WebElement project : projectsBlocks) {
			if (getText(project).contains(projectName)) {
				// getting drop down arrow element after clicking it
				WebElement dropDownBtn = clickDropDownBtn(project);
				// clicking on delete project button
				click(dropDownBtn.findElement(By.cssSelector(".justify-right li:nth-child(9) button")));
				break;
			}
		}
		// click(getProjectDropDownBtn(deleteProject, title));
		click(cancelProjectDeletionBtn);
	}

	@Step("Go to project's analytics page")
	public void clickAnalytics(String projectName) {
		for (WebElement project : projectsBlocks) {
			if (getText(project).contains(projectName)) {
				// getting drop down arrow element after clicking it
				WebElement dropDownBtn = clickDropDownBtn(project);
				// clicking on Analytics button
				click(dropDownBtn.findElement(By.cssSelector(".justify-right li:nth-child(4) a")));
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
		return workspacesBlocks.size();
	}

	@Step("Get number of existing projects")
	public int getProjectsNumber() {
		return projectsBlocks.size();
	}

	public String getTitle() {
		return getText(projectPageTitle);
	}

	// clicks on a specific project drop down arrow and returns the element
	private WebElement clickDropDownBtn(WebElement project) {
		WebElement dropDownBtn = project.findElement(By.cssSelector(".justify-right button svg"));
		click(dropDownBtn);
		return dropDownBtn;
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
