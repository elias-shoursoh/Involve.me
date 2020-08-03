package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class AnalyticsPage extends BasePage {

	@FindBy(css = ".mr-1.text-gray-600")
	private WebElement resultsBtn; // in case a project was selected
	@FindBy(css = ".pt-3.ml-auto")
	private WebElement exportDataBtn;

	// constructor
	public AnalyticsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click results for selected project")
	public void clickResultsBtn() {
		click(resultsBtn);
	}

	@Step("Click Export Data button")
	public void clickExportData() {
		click(exportDataBtn);
	}
}
