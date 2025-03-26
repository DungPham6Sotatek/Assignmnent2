package automation.contanst;

public class CT_MagnetoLoginPage {
	public static final String SIGNIN_LINK = "(//a[normalize-space() = 'Sign In'])[1]";
	public static final String EMAIL_INPUT = "email";
	public static final String PASS_INPUT = "pass";
	public static final String SIGNIN_BTN = "send2";
	public static final String MEN_CATEGORY = "ui-id-5";
	public static final String MEN_TOPS_CATEGORY = "//a[text() = 'Tops']";
	public static final String MEN_JACKETS_TOP_CATEGORY = "//a[text() = 'Jackets']";
	public static final String PROD_LINK = "//ol[@class ='products list items product-items']/li[1]";
	public static final String JACKET_SIZE = "option-label-size-143-item-166";
	public static final String PROD_COLOR = "option-label-color-93-item-49";
	public static final String QTY_INPUT = "qty";
	public static final String ADD_BTN = "product-addtocart-button";
	public static final String SHOW_CART_BTN = "//a[@class = 'action showcart']";
	public static final String PROCEED_TO_CHECKOUT =  "top-cart-btn-checkout";
	public static final String PANTS_LINK = "//a[text() = 'Pants']";
	public static final String PANTS_SIZE = "option-label-size-143-item-175";
	public static final String NEXT_BTN = "//button[@class= 'button action continue primary']";
	public static final String PROD_NAME = "//span[@class= 'base']";
	public static final String PROD_UNIT_PRICE = "//span[@class= 'base']/ancestor::div[@class = 'product-info-main']//span[@class = 'price']";
	
	
	//show details
	public static final String SHOW_DETAILS = "//div[@class = 'title']";
	
	//order summary
	public static final String FIRST_PROD_NAME = "(//strong[@class = 'product-item-name'])[1]";
	public static final String FIRST_PROD_TOTAL_PRICE = "(//strong[@class = 'product-item-name'])[1]/parent::div/following-sibling::div//span[@class = 'cart-price']";
	
	public static final String SECOND_PROD_NAME = "(//strong[@class = 'product-item-name'])[2]";
	public static final String SECOND_PROD_TOTAL_PRICE = "(//strong[@class = 'product-item-name'])[2]/parent::div/following-sibling::div//span[@class = 'cart-price']";
	
	public static final String TOTAL_BILL = "//strong[text() = 'Order Total']/parent::th/following-sibling::td//span";
	
	public static final String SHIP_PRICE = "//span[@data-th= 'Shipping']";
	
	public static final String PLACE_ORDER_BTN = "//button[@title = 'Place Order']";
	
	public static final String ORDER_NUMBER = "//p[text() = 'Your order number is: ']//strong";
	
	public static final String ACTION_SWITCH = "(//span[@class = 'customer-name']//button[@class = 'action switch'])[1]";
	
	public static final String WISH_LIST = "(//li[@class = 'link wishlist']/a)[1]";
	
	public static final String MY_ORDER_LINK = "//a[text() = 'My Orders']";
	
	public static final String MY_ORDER_ID = "(//td[@class = 'col id'])[1]"; 
	
}
