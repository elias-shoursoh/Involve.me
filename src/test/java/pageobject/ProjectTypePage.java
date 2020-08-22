package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ProjectTypePage extends TopNavigateBar {

	@FindBy(css = ".blank div.icon")
	private WebElement startFromScratchBtn;
	@FindBy(css = "#app-layout div:nth-child(3) .title")
	private List<WebElement> projectsBlocks;

	// constructor
	public ProjectTypePage(WebDriver driver) {
		super(driver);
	}

	@Step("Select project {projectName} from projects menu")
	public void selectProject(String projectName) {
		for (WebElement project : projectsBlocks) {
			if (getText(project).equalsIgnoreCase(projectName)) {
				click(project);
			}
		}
	}

	@Step("Click Start from scratch button")
	public void clickStartFromScratchBtn() {
		click(startFromScratchBtn);
	}
}
