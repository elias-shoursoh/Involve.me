package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

/*Top navigation bar POM*/
public class TopNavigateBar extends BasePage {

	@FindBy(css = "#nav-dropdown")
	private WebElement accountBtn;
	@FindBy(css = "[href=\"https://app.involve.me/logout\"]")
	private WebElement logoutBtn;
	@FindBy(css = "[href=\"https://app.involve.me/projects\"]")
	private WebElement projectsBtn;
	@FindBy(css = "[href=\"https://app.involve.me/templates\"]")
	private WebElement templatesBtn;
	@FindBy(css = "[href=\"https://app.involve.me/analytics\"]")
	private WebElement analyticsBtn;
	@FindBy(css = "[href=\"https://app.involve.me/integrations\"]")
	private WebElement integrationsBtn;
	@FindBy(css = "[href=\"https://app.involve.me/affiliate\"]")
	private WebElement affiliateBtn;

	// constructor
	public TopNavigateBar(WebDriver driver) {
		super(driver);
	}

	@Step("Click Projects tab")
	public void clickProjects() {
		click(projectsBtn);
	}

	@Step("Click Templates tab")
	public void clickTemplates() {
		click(templatesBtn);
	}

	@Step("Click Analytics tab")
	public void clickAnalytics() {
		click(analyticsBtn);
	}

	@Step("Click Integrations tab")
	public void clickIntegrations() {
		click(integrationsBtn);
	}

	@Step("Click Affiliate Program tab")
	public void clickAffiliateProgram() {
		click(affiliateBtn);
	}

	@Step("Click Account tab then click logout")
	public void logout() {
		click(accountBtn);
		wait.until(ExpectedConditions.visibilityOf(logoutBtn));
		click(logoutBtn);
	}
}
