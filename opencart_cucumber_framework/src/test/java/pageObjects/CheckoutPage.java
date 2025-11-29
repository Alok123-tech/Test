//package pageObjects;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.Select;
//
//public class CheckoutPage extends BasePage{
//
//	
//	public CheckoutPage(WebDriver driver)
//	{
//		super(driver);
//	}	
//	
//	@FindBy(xpath="//input[@id='input-payment-firstname']")
//	WebElement txtfirstName;
//	
//	@FindBy(xpath="//input[@id='input-payment-lastname']")
//	WebElement txtlastName;
//	
//
//	@FindBy(xpath="//input[@id='input-payment-address-1']")
//	WebElement txtaddress1;
//	
//	@FindBy(xpath="//input[@id='input-payment-address-2']")
//	WebElement txtaddress2;
//	
//	
//	@FindBy(xpath="//input[@id='input-payment-city']")
//	WebElement txtcity;
//	
//	
//	@FindBy(xpath="//input[@id='input-payment-postcode']")
//	WebElement txtpin;
//	
//	
//	@FindBy(xpath="//select[@id='input-payment-country']")
//	WebElement drpCountry;
//	
//	
//	@FindBy(xpath="//select[@id='input-payment-zone']")
//	WebElement drpState;
//
//	@FindBy(xpath="//input[@id='button-payment-address']")
//	WebElement btncontinueBillingAddress;
//	
//	@FindBy(xpath="//input[@id='button-shipping-address']")
//	WebElement btncontinueDeliveryAddress;
//	
//	@FindBy(xpath="//textarea[@name='comment']")
//	WebElement txtDeliveryMethod;
//	
//	@FindBy(xpath="//input[@id='button-shipping-method']")
//	WebElement btncontinueShippingAddress;
//	
//	
//	@FindBy(xpath="//input[@name='agree']")
//	WebElement chkboxTerms;
//	
//	
//	@FindBy(xpath="//input[@id='button-payment-method']")
//	WebElement btncontinuePaymentMethod;
//	
//	
//	@FindBy(xpath="//strong[text()='Total:']//following::td")
//	WebElement lblTotalPrice;
//	
//	
//	@FindBy(xpath="//input[@id='button-confirm']")
//	WebElement btnConfOrder;
//	
//	
//	@FindBy(xpath="//*[@id='content']/h1")
//	WebElement lblOrderConMsg;
//	
//	
//
//	public void setfirstName(String firstName) {
//		txtfirstName.sendKeys(firstName);
//	}
//
//
//	public void setlastName(String lastName) {
//		txtlastName.sendKeys(lastName);
//	}
//
//
//	public void setaddress1(String address1) {
//		txtaddress1.sendKeys(address1);
//	}
//
//
//	public void setaddress2(String address2) {
//		txtaddress2.sendKeys(address2);
//	}
//
//
//	public void setcity(String city) {
//		txtcity.sendKeys(city);
//	}
//
//
//	public void setpin(String pin) {
//		txtpin.sendKeys(pin);
//	}
//
//
//	public void setCountry(String Country) {
//		new Select(drpCountry).selectByVisibleText(Country);
//	}
//
//
//	public void setState(String State) {
//		new Select(drpState).selectByVisibleText(State);
//	}
//	
//	public void clickOnContinueAfterBillingAddress()
//	{
//		btncontinueBillingAddress.click();
//	}
//	
//	public void clickOnContinueAfterDeliveryAddress()
//	{
//		btncontinueDeliveryAddress.click();
//	}
//	
//	
//	public void setDeliveryMethodComment(String deliverymsg)
//	{
//		txtDeliveryMethod.sendKeys(deliverymsg);
//		
//	}
//	
//	public void clickOnContinueAfterDeliveryMethod()
//	{
//		btncontinueShippingAddress.click();
//	}
//	
//	public void selectTermsAndConditions()
//	{
//		chkboxTerms.click();
//	}
//	
//	public void clickOnContinueAfterPaymentMethod()
//	{
//		btncontinuePaymentMethod.click();
//	}
//	
//	public String  getTotalPriceBeforeConfOrder()
//	{
//		return lblTotalPrice.getText(); //$207.00
//		
//	}
//	
//	public void clickOnConfirmOrder() {
//		btnConfOrder.click();
//	}
//	
//	public boolean isOrderPlaced() throws InterruptedException
//	{
//		try
//		{
//		driver.switchTo().alert().accept();
//		Thread.sleep(2000);
//		btnConfOrder.click();
//		Thread.sleep(3000);
//		if(lblOrderConMsg.getText().equals("Your order has been placed!"))
//			return true;
//		else
//			return false;
//		}catch(Exception e)
//		{
//			return false;
//		}
//		
//	}
//	
//}
package pageObjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
	public WebDriver driver;

	public CheckoutPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	By cartBag = By.cssSelector("[alt='Cart']");
	By checkOutButton = By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]");
	By promoBtn = By.cssSelector(".promoBtn");
	By placeOrder = By.xpath("//button[contains(text(),'Place Order')]");
	By cost = By.xpath("//*[contains(text(),'Apple iPhone 14 (Blue, 128 GB)')]/../following-sibling::div[@class='col col-5-12 BfVC2z']/descendant::div[@class='Nx9bqj _4b5DiR']");
    By Listbox = By.xpath("//select[@id='colors']");
    By dynamicTable = By.xpath( "//table[@class='table table-striped']//tbody//tr");
    By link = By.xpath("//a[@class='blinkingText']");
    By childLink =By.xpath("//a[contains(text(),'mentor@rahulshettyacademy.com')]");
	
	
	public void clickLink() {
		
//		driver.findElement(link).click();
	}
	
public boolean verifyChildWindowTitle() {
	driver.findElement(link).click();
		Set<String> se =driver.getWindowHandles();
//		List<String> ai = new ArrayList<>(se);
	
		
		for(String s : se) {
			
			 driver.switchTo().window(s);
			 String title = driver.getTitle();
			System.out.println(title);
			 if (title.equals("RS Academ")) {
		            boolean flag = driver.findElement(childLink).isEnabled();
		            return flag;
			 }
//
}
		return false;
	}
    
	public void CheckoutItems()
	{
		driver.findElement(cartBag).click();
		driver.findElement(checkOutButton).click();
	}
	
	public Boolean VerifyPromoBtn()
	{
		return driver.findElement(promoBtn).isDisplayed();
	}
	
	public Boolean VerifyPlaceOrder()
	{
		return driver.findElement(placeOrder).isDisplayed();
	}
	
	
//	public Set<String> verifyduplicateItems()
//	{
//		
//	WebElement listboxws = driver.findElement(Listbox);
//	Select sel = new Select(listboxws);
//  Set<String> se= new HashSet<>();
//  for(WebElement ele : sel.getOptions()) {
//  String options= 	ele.getText();
//  if(!se.add(options)) {
//    	System.out.println(options);
// 
//   
//    }
//		return se;
//		
//	}
	
	public String getCost()
	{
		String mobilecost =  driver.findElement(cost).getText();
		StringBuilder sb = new StringBuilder();
//		   Actions actions = new Actions(driver);
		   By placeOrder = By.xpath("//button[contains(text(),'Place Order')]");
           // Perform right-click (context click)
//           actions.contextClick((WebElement) cartBag).perform();
		   for(int i =0;i<mobilecost.length();i++) {
			   
			   if(mobilecost.charAt(i)!='"') {
				   
				   sb.append(mobilecost.charAt(i));
			   }
			   
			   
		   }
		   
		   mobilecost = sb.toString();
		   //mobilecost.replaceAll("\"", "");
           System.out.println("finnnnnnnnnnnnnnnnnn" + mobilecost);
           return mobilecost;
	}

	public void verifyData()
	{
	
	  List<WebElement> li = driver.findElements(dynamicTable);
	  int count = li.size();

	  
	  for(int i =0 ;i<=count ;i++) {
		  
		  WebElement name =driver.findElement(By.xpath("//table[@class='table table-striped']//tbody//tr[i]//td[1]"));
		  
		 System.out.println( name.getText());
		 String names = name.getText();
		 if(names.equals("Chrome")) {
			 
			 String cpuName =driver.findElement(By.xpath("//td[normalize-space()='Chrome']//following-sibling::td[contains(text(),'%')]")).getText();
	
			 String cpuValue =driver.findElement(By.xpath("//p[@id='chrome-cpu']")).getText();
				
		
		 
		 if(cpuValue.contains(cpuName)) {
			 System.out.println("Equal");
			 
		 }
		 else {
			 System.out.println("NotEqual");
			 }
		 
		 break;
	  }
}
	
}
	
}
