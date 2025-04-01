package automation.testsuite;

import automation.common.CommonBase;
import automation.contanst.CT_MagnetoLoginPage;
import automation.contanst.CT_PageURL;
import automation.pageLocator.MagnetoLoginPageFactory;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Section2_MagnetoTestStore extends CommonBase {
	String email = "dung.pham6@sotatek.com";
	String pass = "Dung271095@";
	String jacketsQty = "2";
	String pantsQty = "1";
	String jacketName;
	String jacketUnitPrice;
	String pantName;
	String pantUnitPrice;
	String firstProductName;
	String SecondProductName;
	String orderNumber;

	@BeforeMethod
	@Parameters("browserTestNG") // tên param trong pt test mà file testNG.xml ánh xạ tới
	public void openBrowser(String browserTestNG) { 
		setUpDriver(browserTestNG);
		driver.get(CT_PageURL.MAGNETO_URL);
	}
	
	@Test (priority = 1)
	public void placeOrderSuccessfully() {
		//login
		MagnetoLoginPageFactory magnetoLogin = new MagnetoLoginPageFactory(driver);
		magnetoLogin.LoginSucessfully(email, pass);
		
		//find jackets
		magnetoLogin.findJackets(jacketsQty);
		jacketName = magnetoLogin.getProdName(By.xpath(CT_MagnetoLoginPage.PROD_NAME));
		jacketUnitPrice = magnetoLogin.getProdPrice(By.xpath(CT_MagnetoLoginPage.PROD_UNIT_PRICE));
		System.out.println(jacketName);
		System.out.println("unit price: " +jacketUnitPrice);
		
		// calculate total first product price
		String totalFirstProdPrice =  magnetoLogin.totalPayOneProduct(jacketUnitPrice, jacketsQty);
		System.out.println("total price: " +totalFirstProdPrice);
		
		//add jackets to cart
		magnetoLogin.addToCart();
		
		//find pants
		magnetoLogin.findPants(pantsQty);
		pantName = magnetoLogin.getProdName(By.xpath(CT_MagnetoLoginPage.PROD_NAME));
		pantUnitPrice = magnetoLogin.getProdPrice(By.xpath(CT_MagnetoLoginPage.PROD_UNIT_PRICE));
		System.out.println(pantName);
		System.out.println("unit price: " + pantUnitPrice);
		
		//calculate total second price
		String totalSecondProdPrice = magnetoLogin.totalPayOneProduct(pantUnitPrice, pantsQty);
		System.out.println("total price: " +totalSecondProdPrice);
		
		
		
		// add pant to cart
		magnetoLogin.addToCart();
		 //place order
		magnetoLogin.showCart();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.SHIP_PRICE)));
		String totalShip = driver.findElement(By.xpath(CT_MagnetoLoginPage.SHIP_PRICE)).getText();
		String orderTotal = magnetoLogin.orderTotal(totalFirstProdPrice, totalSecondProdPrice,totalShip);
		System.out.println(orderTotal);
		
		//assert - 3
		//first prod
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.FIRST_PROD_NAME)));
		assertEquals(driver.findElement(By.xpath(CT_MagnetoLoginPage.FIRST_PROD_NAME)).getText(), jacketName);
		//total first prod price
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.FIRST_PROD_TOTAL_PRICE)));
		assertEquals(driver.findElement(By.xpath(CT_MagnetoLoginPage.FIRST_PROD_TOTAL_PRICE)).getText(), totalFirstProdPrice);
		
		//second prod
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.SECOND_PROD_NAME)));
		assertEquals(driver.findElement(By.xpath(CT_MagnetoLoginPage.SECOND_PROD_NAME)).getText(), pantName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.SECOND_PROD_TOTAL_PRICE)));
		assertEquals(driver.findElement(By.xpath(CT_MagnetoLoginPage.SECOND_PROD_TOTAL_PRICE)).getText(), totalSecondProdPrice);
		
		//
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.TOTAL_BILL)));
		assertEquals(driver.findElement(By.xpath(CT_MagnetoLoginPage.TOTAL_BILL)).getText(), orderTotal);
		
		//4
		// place order and get order number
		orderNumber = magnetoLogin.placeOrder();
		System.out.println("Order number: "+orderNumber);
		
		
		// access my order
		magnetoLogin.accessMyOrder();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CT_MagnetoLoginPage.MY_ORDER_ID)));
		assertEquals(driver.findElement(By.xpath(CT_MagnetoLoginPage.MY_ORDER_ID)).getText(), orderNumber);
	}
	
	 
}
