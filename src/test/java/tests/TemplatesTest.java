package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageobject.AboutPage;
import pageobject.LoginPage;
import pageobject.ProjectTypePage;
import pageobject.ProjectsPage;
import pageobject.TemplatesPage;
import utils.Configuration;

@Severity(SeverityLevel.NORMAL)
public class TemplatesTest extends BaseTest {

	@Test(priority = 1, alwaysRun = true, description = "Log in to account")
	public void logIn() {
		AboutPage ap = new AboutPage(driver);
		ap.clickLoginLink();
		LoginPage lp = new LoginPage(driver);
		lp.logIn(Configuration.readProperty("username"), Configuration.readProperty("password"));
		ProjectsPage pp = new ProjectsPage(driver);
		Assert.assertEquals(pp.getTitle(), "My Workspace");
		pp.createNewProject();
		ProjectTypePage ptp = new ProjectTypePage(driver);
		ptp.selectProject("quiz");
	}

	@Test(priority = 2, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in All equals number of templates in page")
	@Story("When in Templates page, the number displayed next to All category should equal the number of templates in page")
	@Description("All category number displayed equals number of templates displayed in page")
	public void AllCategoryTemplatesNumberTest() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickAll();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("all") == tp.getTemplatesNumber());
	}

	@Test(priority = 3, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Quiz equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Quiz category should equal the number of templates in page")
	@Description("Quiz category number displayed equals number of templates displayed in page")
	public void QuizCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickQuiz();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("quiz") == tp.getTemplatesNumber());
	}

	@Test(priority = 4, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Survey equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Survey category should equal the number of templates in page")
	@Description("Survey category number displayed equals number of templates displayed in page")
	public void SurveyCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickSurvey();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("survey") == tp.getTemplatesNumber());
	}

	@Test(priority = 5, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Calculator equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Calculator category should equal the number of templates in page")
	@Description("Calculator category number displayed equals number of templates displayed in page")
	public void CalculatorCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickCalculator();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("calculator") == tp.getTemplatesNumber());
	}

	@Test(priority = 6, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Form equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Form category should equal the number of templates in page")
	@Description("Form category number displayed equals number of templates displayed in page")
	public void FormCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickForm();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("form") == tp.getTemplatesNumber());
	}

	@Test(priority = 7, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Payment Form equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Payment Form category should equal the number of templates in page")
	@Description("Payment Form category number displayed equals number of templates displayed in page")
	public void PaymentFormCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickPaymentForm();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("payment form") == tp.getTemplatesNumber());
	}

	@Test(priority = 8, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Lead Page equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Lead Page category should equal the number of templates in page")
	@Description("Lead Page category number displayed equals number of templates displayed in page")
	public void LeadPageCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickLeadPage();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("lead page") == tp.getTemplatesNumber());
	}

	@Test(priority = 9, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Promotion equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Promotion category should equal the number of templates in page")
	@Description("Promotion category number displayed equals number of templates displayed in page")
	public void PromotionCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickPromotion();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("promotion") == tp.getTemplatesNumber());
	}

	@Test(priority = 10, dependsOnMethods = {
			"logIn" }, description = "Test if number of categories displayed in Personality Test equals number of templates in page")
	@Story("When in Templates page, the number displayed next to Personality Test category should equal the number of templates in page")
	@Description("Personality Test category number displayed equals number of templates displayed in page")
	public void PersonalityTestCategoryTemplatesNumberTes() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.clickPersonalityTest();
		Assert.assertTrue(tp.getCategoryDisplayedNumber("personality test") == tp.getTemplatesNumber());
	}

	@Test(priority = 11, alwaysRun = true, description = "Log out of account")
	public void logout() {
		TemplatesPage tp = new TemplatesPage(driver);
		tp.logout();
		LoginPage lp = new LoginPage(driver);
		Assert.assertEquals(lp.getTitle(), "Log in");
	}
}
