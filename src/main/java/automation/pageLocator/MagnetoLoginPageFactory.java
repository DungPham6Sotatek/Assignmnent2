package automation.pageLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;

import automation.common.CommonBase;
import automation.contanst.CT_MagnetoLoginPage;

public class MagnetoLoginPageFactory extends CommonBase{
	private WebDriver driver;
	public String jacketName;
	public String jacketUnitPrice;
	public String pantName;
	public String pantUnitPrice;
	
	public MagnetoLoginPageFactory(WebDriver _driver) {
		this.driver = _driver;
	}
	
	
	public void LoginSucessfully(String email, String pass) {
		if (driver == null) {
	        throw new IllegalStateException("Driver is not initialized");
	    }
		clickToElement(By.xpath(CT_MagnetoLoginPage.SIGNIN_LINK));
		typeKeysToElemenyByLocator(By.id(CT_MagnetoLoginPage.EMAIL_INPUT), email);
		typeKeysToElemenyByLocator(By.id(CT_MagnetoLoginPage.PASS_INPUT), pass);
		clickToElement(By.id(CT_MagnetoLoginPage.SIGNIN_BTN));
	}
	public void findJackets(String jacketQty) {
		//choose jackets
		clickToElement(By.id(CT_MagnetoLoginPage.MEN_CATEGORY));
		hoverToElement(By.xpath(CT_MagnetoLoginPage.MEN_TOPS_CATEGORY));
		clickToElement(By.xpath(CT_MagnetoLoginPage.MEN_JACKETS_TOP_CATEGORY));
		
		//open cart screen
		scrollToElement(By.xpath(CT_MagnetoLoginPage.PROD_LINK));
		clickToElement(By.xpath(CT_MagnetoLoginPage.PROD_LINK));
		
		//choose size, color, quantity
		clickToElement(By.id(CT_MagnetoLoginPage.JACKET_SIZE));
		clickToElement(By.id(CT_MagnetoLoginPage.PROD_COLOR));
		typeKeysToElemenyByLocator(By.id(CT_MagnetoLoginPage.QTY_INPUT), jacketQty);
//		clickToElement(By.id(CT_MagnetoLoginPage.ADD_BTN));
		
		//choose men category
		
		//click pants link
		
		pauseBrowser(2000);
		//choose size, color, quantity
		
		
		
//		clickToElement(By.id(CT_MagnetoLoginPage.ADD_BTN));

		
		
	}
	public void findPants(String pantQty) {
		clickToElement(By.id(CT_MagnetoLoginPage.MEN_CATEGORY));
		clickToElement(By.xpath(CT_MagnetoLoginPage.PANTS_LINK));
		clickToElement(By.xpath(CT_MagnetoLoginPage.PROD_LINK));
		clickToElement(By.id(CT_MagnetoLoginPage.PANTS_SIZE));
		clickToElement(By.id(CT_MagnetoLoginPage.PROD_COLOR));
		typeKeysToElemenyByLocator(By.id(CT_MagnetoLoginPage.QTY_INPUT), pantQty);
		pauseBrowser(2000);
	}
	public void addToCart() {
		clickToElement(By.id(CT_MagnetoLoginPage.ADD_BTN));
	}
	public String getProdName (By locator) {
		String prodName;
		return prodName = getText(locator);
	}
	public String getProdPrice (By locator) {
		String jacketPrice;
		return jacketPrice = getText(locator);
	}
	public void showCart() {
		clickToElement(By.xpath(CT_MagnetoLoginPage.SHOW_CART_BTN));
		clickToElement(By.id(CT_MagnetoLoginPage.PROCEED_TO_CHECKOUT));
		scrollToElement(By.xpath(CT_MagnetoLoginPage.NEXT_BTN));
		pauseBrowser(3000);
		clickToElement(By.xpath(CT_MagnetoLoginPage.NEXT_BTN));
		clickByJSExecutor(By.xpath(CT_MagnetoLoginPage.SHOW_DETAILS));
		
	}
	
	
	public String totalPayOneProduct(String unitPrice, String Qty) {
		String cleanUnitPrice = unitPrice.replaceAll("[^0-9.]", "");
		double convertedUnitPriceToDouble = Double.parseDouble(cleanUnitPrice);
		double convertedQtyValueToDouble = Double.parseDouble(Qty);	
		double totalPay = convertedUnitPriceToDouble * convertedQtyValueToDouble;
		String convertedTotalPayToString = String.format("$%.2f", totalPay);
		return convertedTotalPayToString;
	}
	
	public String orderTotal (String fisrtProd, String secondProd, String shipPrice) {
		String cleanFirstProd = fisrtProd.replaceAll("[^0-9.]", "");
		String cleanSecondProd = secondProd.replaceAll("[^0-9.]", "");
		String cleanShipPrice = shipPrice.replaceAll("[^0-9.]", "");
		double convertFirstProdToDouble = Double.parseDouble(cleanFirstProd);
		double convertSecondProdToDouble = Double.parseDouble(cleanSecondProd);
		double convertShipPriceToDouble = Double.parseDouble(cleanShipPrice);
		double orderTotal = convertFirstProdToDouble + convertSecondProdToDouble + convertShipPriceToDouble;
		String convertOrderTotalToString = String.format("$%.2f", orderTotal);
		return convertOrderTotalToString;
	}
	public String placeOrder() {
		clickByJSExecutor(By.xpath(CT_MagnetoLoginPage.PLACE_ORDER_BTN));
		pauseBrowser(3000);
		return getText(By.xpath(CT_MagnetoLoginPage.ORDER_NUMBER));
		
	}
	public void accessMyOrder() {
		clickToElement(By.xpath(CT_MagnetoLoginPage.ACTION_SWITCH));
		clickToElement(By.xpath(CT_MagnetoLoginPage.WISH_LIST));
		clickByJSExecutor(By.xpath(CT_MagnetoLoginPage.MY_ORDER_LINK));
		pauseBrowser(3000);
	}
}
