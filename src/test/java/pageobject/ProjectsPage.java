package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends BasePage {
	
	@FindBy(css="#app .px-4 a")
	private WebElement startBtn;
	@FindBy(css=".font-medium button")
	private WebElement createNewWorkspaceBtn;
	@FindBy(css="[data-icon=\"chevron-down\"]")
	private WebElement workspaceEditBtn;
	
	public ProjectsPage(WebDriver driver) {
		super(driver);
	}

}
