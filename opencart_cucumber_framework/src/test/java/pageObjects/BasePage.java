package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	 WebDriver driver;
	    
	   public BasePage(WebDriver driver)
	     {
		     this.driver=driver;
		     PageFactory.initElements(driver,this);
		     
		     
	     }
	   	   
}
//
//
//PageFactory.initElements() is a method provided by the Selenium WebDriver library in Java that initializes all the elements defined in a page object class.
//
//When using Selenium WebDriver to automate web testing, it's a common practice to create a separate class for each web page you're testing. These classes are called "page objects" and they contain the web elements (such as buttons, input fields, links, etc.) and the methods that allow you to interact with those elements.
//
//PageFactory.initElements() is used to initialize the web elements defined in a page object class by finding them on the web page and storing them as member variables of the class. This allows you to easily interact with those elements in your test code without having to find them every time you need to use them.
//
//Here's an example of how PageFactory.initElements() can be used in a page object class:


