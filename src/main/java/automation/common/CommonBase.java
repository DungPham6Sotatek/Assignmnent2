package automation.common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonBase {
	public static WebDriver driver;
	public int initWaitTime = 30;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	
	
	public WebDriver initChromeBrowser(String url) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-software-rasterizer");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return driver;
	}
	
	public WebElement getElementOnlyPresentDom(By locator) {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
		
	}
	public WebElement getElementVisibility (By locator) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}
	
	public List<WebElement> getAllElementsOnlyPresentDom(By locator) {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElements(locator);
		
	}
	public List<WebElement> getAllElementsVisibility(By locator) {
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return driver.findElements(locator);
		
	}
	public void clickToElement(By locator) {
		
		WebElement element = getElementVisibility(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public String getText(By locator) {
		String result;
		WebElement element = getElementVisibility(locator);
		wait.until(ExpectedConditions.visibilityOf(element));
		return  result = element.getText();
	}
	
	public void typeKeysToElemenyByLocator (By locator ,String key) {
		
		WebElement element = getElementVisibility(locator); 
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(key);
	}
	
//	public void typeKeysToElementByElement (WebElement element ,String key) {
//		
//		wait.until(ExpectedConditions.visibilityOf(element));
//		element.clear();
//		element.sendKeys(key);
//	}
	
	public void pauseBrowser(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public boolean isElementPresent(By locator) {
	
		WebElement element = getElementVisibility(locator); 
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (NoSuchElementException e1) {
			return false;
		} catch (TimeoutException e2) {
			return false;
		}
		
		
	}
	// java script executor
	public void clickByJSExecutor (By locator) {
		WebElement element = getElementOnlyPresentDom(locator); 
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	public void typeTextJS_ToElementByValueAttribute(By locator, String value) {
		try {
			WebElement element = getElementOnlyPresentDom(locator); 
			jsExecutor.executeScript("arguments[0].value = '" + value + "'" , element);
		} catch (StaleElementReferenceException ex) {
			typeTextJS_ToElementByValueAttribute(locator, value);
		}
		
	}
	public void typeTextJS_ToElementByInnerHTMLAttribute(By locator, String value) {
		try {
			WebElement element = getElementOnlyPresentDom(locator);
			jsExecutor.executeScript("arguments[0].innerHTML = '" + value + "'", element);
		} catch (StaleElementReferenceException ex) {
			// TODO: handle exception
			typeTextJS_ToElementByInnerHTMLAttribute(locator, value);
		}
	}
	
	public void scrollToElement(By locator) {
		WebElement element = getElementOnlyPresentDom(locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	public void scrollDownToBottom () {
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void hoverToElement(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementVisibility(locator));
	}
}
