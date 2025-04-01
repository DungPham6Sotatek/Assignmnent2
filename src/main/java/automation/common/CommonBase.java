package automation.common;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonBase {
	public static WebDriver driver;
	public int initWaitTime = 50;
	
	

	
	
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
	
	public WebDriver initFirefoxBrowser(String url) {
		 WebDriverManager.firefoxdriver().clearDriverCache().setup();
		    FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--disable-gpu");
		    options.addArguments("--disable-software-rasterizer");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-dev-shm-usage");
		    driver = new FirefoxDriver(options);
		    driver.get(url);
		    driver.manage().window().maximize();
		    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		    return driver;
	}
	
	public WebDriver initEdgeBrowser(String url) {
	    WebDriverManager.edgedriver().setup();
	    EdgeOptions options = new EdgeOptions();
	    options.addArguments("--disable-gpu");
	    options.addArguments("--disable-software-rasterizer");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    driver = new EdgeDriver(options);
	    driver.get(url);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	    return driver;
	}
	
	public WebElement getElementOnlyPresentDom(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
		
	}
	public WebElement getElementVisibility (By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((locator)));
		return driver.findElement(locator);
	}
	
	public List<WebElement> getAllElementsOnlyPresentDom(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElements(locator);
		
	}
	public List<WebElement> getAllElementsVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return driver.findElements(locator);
		
	}
	public void clickToElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = getElementVisibility(locator);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public String getText(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String result;
		WebElement element = getElementVisibility(locator);
		wait.until(ExpectedConditions.visibilityOf(element));
		return  result = element.getText();
	}
	
	public void typeKeysToElemenyByLocator (By locator ,String key) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElementOnlyPresentDom(locator); 
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	public void typeTextJS_ToElementByValueAttribute(By locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		try {
			WebElement element = getElementOnlyPresentDom(locator); 
			jsExecutor.executeScript("arguments[0].value = '" + value + "'" , element);
		} catch (StaleElementReferenceException ex) {
			typeTextJS_ToElementByValueAttribute(locator, value);
		}
		
	}
	public void typeTextJS_ToElementByInnerHTMLAttribute(By locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		try {
			WebElement element = getElementOnlyPresentDom(locator);
			jsExecutor.executeScript("arguments[0].innerHTML = '" + value + "'", element);
		} catch (StaleElementReferenceException ex) {
			// TODO: handle exception
			typeTextJS_ToElementByInnerHTMLAttribute(locator, value);
		}
	}
	
	public void scrollToElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		   if (driver == null) {
		        System.out.println("Driver is not initialized");
		        return;  // Tránh việc gọi findElement khi driver là null
		    }
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement element = getElementVisibility(locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	public void scrollDownToBottom () {
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void hoverToElement(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementVisibility(locator));
	}
	
	private WebDriver initChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-software-rasterizer");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		return driver;
	}
	
	private WebDriver initFirefoxBrowser() {
		 WebDriverManager.firefoxdriver().clearDriverCache().setup();
		    FirefoxOptions options = new FirefoxOptions();
		    options.addArguments("--disable-gpu");
		    options.addArguments("--disable-software-rasterizer");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-dev-shm-usage");
		    driver = new FirefoxDriver(options);
		   
		    driver.manage().window().maximize();
		    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		    return driver;
	}
	
	private WebDriver initEdgeBrowser() {
	    WebDriverManager.edgedriver().setup();
	    EdgeOptions options = new EdgeOptions();
	    options.addArguments("--disable-gpu");
	    options.addArguments("--disable-software-rasterizer");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-dev-shm-usage");
	    driver = new EdgeDriver(options);
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	    return driver;
	}
	
	public WebDriver setUpDriver(String browserName) {
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			driver = initChromeBrowser();
			break;
		case "firefox":
			driver = initFirefoxBrowser();
			break;
		case "edge":
			driver = initEdgeBrowser();
			break;
		default:
			System.out.println("Browser: " +browserName+ " is invalid, launching Chrome as default");
			driver = initFirefoxBrowser();
			break;
		}
		return driver;
	}
	public static WebDriver getDriver() {
		return driver;
	}
}
