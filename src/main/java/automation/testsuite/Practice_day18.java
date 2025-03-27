package automation.testsuite;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import automation.common.CommonBase;
import automation.contanst.CT_PageURL;

public class Practice_day18 extends CommonBase{
	
	@BeforeMethod
	public void openBrowser() {
		driver = setUpDriver("chrome");
		driver.get(CT_PageURL.CODESTART_URL);
		
	}
	
	@Test
	public void consultedLogin() {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//h2[contains(text(), 'Đăng kí nhận tư vấn lộ trình phát triển')]")));
		scrollToElement(By.xpath("//h2[contains(text(), 'Đăng kí nhận tư vấn lộ trình phát triển')]"));
		driver.switchTo().frame(getElementVisibility(By.xpath("(//iframe[@title = 'Khách hàng'])[1]")));
		pauseBrowser(3000);
		typeKeysToElemenyByLocator(By.id("name"), "Pham Do Viet Dung");
	}
}
