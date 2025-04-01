package automation.testsuite;


import static org.testng.Assert.assertTrue;



import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.*;



import automation.common.CommonBase;
import automation.common.helpers.CaptureHelper;
import automation.common.helpers.ExcelHelper;
import automation.common.listener.ReportListener;

import automation.common.logs.Log;
import automation.common.utils.PropertiesFile;
import automation.contanst.CT_AladaLoginPage;
import automation.contanst.CT_PageURL;
import io.qameta.allure.*;



@Listeners(ReportListener.class)
@Epic("Test")
@Feature("Project Test")
public class TestAlada extends CommonBase{
	private ExcelHelper excel;
	@Parameters("browserTestNG")
	@BeforeMethod
	@Description("Open browser")
	public void openBrowser() {
		driver = initFirefoxBrowser(CT_PageURL.ALADA_URL);
		
	}
//	@Test
//	public void loginFailed_1() throws Exception {
//		excel = new ExcelHelper();
//		excel.setExcelFile("src/test/aladaDataTest/Book1.xlsx", "Sheet1");
//		for (int i = 1; i < 6; i++) {
//			typeKeysToElemenyByLocator(By.id(CT_AladaLoginPage.EMAIL_INPUT), excel.getCellData("username", i));
//			typeKeysToElemenyByLocator(By.id(CT_AladaLoginPage.PASS_INPUT), excel.getCellData("password", i));
//			clickToElement(By.xpath(CT_AladaLoginPage.LOGIN_BTN));
//			assertTrue(getElementVisibility(By.xpath(CT_AladaLoginPage.NOT_SIGN_ERROR)).isDisplayed());
//		}
//		
//	}
	@Test
	@Step("Login")
	public void loginFailed_2() {
		PropertiesFile.setPropertiesFile("src/test/configs.properties");
		Log.info("type email");
		typeKeysToElemenyByLocator(By.id(CT_AladaLoginPage.EMAIL_INPUT), PropertiesFile.getPropValue("email"));
		Log.info("type password");
		typeKeysToElemenyByLocator(By.id(CT_AladaLoginPage.PASS_INPUT), PropertiesFile.getPropValue("password"));
		Log.info("Click button Login");
		clickToElement(By.xpath(CT_AladaLoginPage.LOGIN_BTN));
	}
	@AfterMethod
	public void quitBrowser(ITestResult result) {
		if(ITestResult.SUCCESS == result.getStatus()) {
			CaptureHelper capture = new CaptureHelper();
			capture.captureScreenShot(driver, result.getName());
		}
		driver.quit();
	}
}
