package pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectEditPage extends BasePage {

	@FindBy(css = "input#project-name")
	private WebElement projectNameField;
	@FindBy(css = ".swal-button.swal-button--confirm")
	private WebElement startEditingBtn;
	@FindBy(css = "#bs-example-navbar-collapse-1 li:nth-child(4) a")
	private WebElement settingsLinkBtn;
	@FindBy(css = ".e-close.nav-link")
	private WebElement saveAndExitBt;
	@FindBy(css = "#content-item-tab")
	private WebElement contentElementsBtn;
	@FindBy(css = "#tab-design-settings")
	private WebElement designBtn;
	@FindBy(css = ".btn-secondary.save-btn")
	private WebElement publishBtn;
	@FindBy(css = ".btn-secondary.preview-btn")
	private WebElement designPreviewBtn;
	@FindBy(css = ".e-pages-container.fitsNavigation .e-page-management")
	private List<WebElement> pages;
	@FindBy(css = "#tab1contentitems p")
	private List<WebElement> contentList;

	// TODO: finish here with at least one editting of a selected project

	// constructor
	public ProjectEditPage(WebDriver driver) {
		super(driver);
	}

}