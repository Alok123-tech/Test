package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CheckoutPage;
import pageObjects.CheckoutPage3;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utilities.DataReader;

public class steps {
	
     WebDriver driver;
     HomePage hp;
     LoginPage lp;
     MyAccountPage macc;
     CheckoutPage3 checkout;
     public CheckoutPage checkoutPage;
   
     List<HashMap<String, String>> datamap; //Data driven

     public Logger logger; //for logging
     public ResourceBundle rb; // for reading properties file
     public String br; //to store browser name
     public static  int retrycount = 0;
     public static final int Max_Retry = 2;//Set retry attempts
     

    

    @Before
    public void setup() throws IOException    //Junit hook - executes once before starting
    {
        //for logging
      logger= LogManager.getLogger(this.getClass());
        //Reading config.properties (for browser)
//      File src = new File("D:\\opencart_cucumber_framework (1)\\opencart_cucumber_framework\\resources");
//      FileInputStream fis = new FileInputStream(src);
//      Properties prop = new Properties();
//      prop.load(fis);
//      String s123 = prop.getProperty("browser");
//        rb=ResourceBundle.getBundle("config");
//        br=rb.getString("browser");
  
        macc=new MyAccountPage(driver);
        checkoutPage = new CheckoutPage(driver);
        
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties prop1 = new Properties();
        prop1.load(input);
        br = prop1.getProperty("browser");
        System.out.println("hipovqeqeqeqeqeqeqeqeqeqeqe         " + br);
       

    }

//    @AfterStep
//    public void tearDown(Scenario scenario ) {
//        System.out.println("Scenario status ======>"+scenario.getStatus());
//      
//        if(scenario.isFailed()) {
//             byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png",scenario.getName());
//           
//            }
//       driver.quit();
//    }
    
    
    
    @After
    public void tag(Scenario scenario) {
        System.out.println("Scenario status ======> " + scenario.getStatus());

        // Ensure that scenario has the correct tag before taking a screenshot
        if (scenario.isFailed()) { 
            try {
                if (driver != null) {
                	
                	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                	
                	String testcasename = scenario.getName();
                	
                	try {
						FileUtils.copyFile(src, new File("./screenshot/"+testcasename+"md"));
						System.out.println(testcasename);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                }
            } catch (Exception e) {
                System.out.println("Screenshot capture failed: " + e.getMessage());
            }
        }

        // Ensure driver.quit() is only called if driver is not null
        if (driver != null) {
            driver.quit();
            driver = null;  // Reset driver to avoid reuse issues
        }
    }

    @Before
    public void BeforeScenario(Scenario scenario) {
    	if(retrycount>0) {
    		System.out.println(scenario.getName() + "Attempt" +retrycount);
    	}
    }
//    @After
//    public void tag1(Scenario scenario ) throws IOException {
//        System.out.println("Scenario status ======>"+scenario.getStatus());
//      
//        if(scenario.isFailed()) {
//       Screenshot sc = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000))
//    		   .takeScreenshot(driver);
//       // Save the screenshot as a PNG file
//           ImageIO.write(sc.getImage(),"PNG",new File("Screenshot.png"));
//            }
//       driver.quit();
//    }
    @After
    public void afterScenario(Scenario scenario) {
    	if(scenario.isFailed()&& retrycount < Max_Retry) {
    		
    		retrycount++;
    		System.out.println(scenario.getName()+ "Attempsts" +retrycount );
//    		Assume.assumeTrue(false);//force retry
    	}
    	
    	else {
    		
    		retrycount = 0;//Reset the count
    	}
    	
    }

    
//    @After
//    public void tag1(Scenario scenario ) {
//        System.out.println("Scenario status ======>"+scenario.getStatus());
//      
//      if(scenario.getSourceTagNames().equals("")) {
//    	  
//      }
//           
//            
//       driver.quit();
//    }

    @Given("User Launch browser")
    public void user_launch_browser() {
        if(br.equals("chrome"))
        {
          System.setProperty("webdriver.chrome.driver","\\D:\\opencart_cucumber_framework (1)\\opencart_cucumber_framework\\Drivers\\chromedriver.exe");
          WebDriverManager.chromedriver().setup();
          driver=new ChromeDriver();
       
        }
        else if (br.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (br.equals("edge")) {
            driver = new EdgeDriver();
        }
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15000));
        
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Given("opens URL {string}")
    public void opens_url(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }


    @Given("user search for {string}")
        public void user_search_for(String product) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//// Get the shadow host
//        WebElement host = driver.findElement(By.cssSelector("cr-searchbox"));
//
//// Get the shadow root
//        WebElement root = (WebElement) js.executeScript("return arguments[0].shadowRoot", host);
//
//// Use CSS selector inside shadow root
//        WebElement inputBox = root.findElement(By.cssSelector("input#input"));
        WebElement inputBox = driver.findElement(By.xpath("//*[@title=\"Search\"]"));
        inputBox.sendKeys(product);
    }
   
 
    @Then("user click on Link")
    public void clickLink(){
    	 checkoutPage = new CheckoutPage(driver);   
    	checkoutPage.clickLink();
    }
    
    @Then("user verify the title of open window")
    public void verify_the_title(){
    	 checkoutPage = new CheckoutPage(driver);   
    
    	boolean childWindow= checkoutPage.verifyChildWindowTitle();
    	
    	
    	
    	if(childWindow) {
    		
    		logger.info("WindowExist and the link is present");
    		
    		Assert.assertTrue(true);
    	}else {
    		
    		logger.info("Assertion fail");
    		Assert.assertTrue(true);
    	}
    	    }

    @When("User navigate to MyAccount ")
    public void user_navigate_to_my_account() {
//    	hp=new HomePage(driver);
//    	HomePage.clickMyAccount();
        logger.info("Clicked on My Account ");
        
            
    }

    @When("click on Login")
    public void click_on_login() {
    	hp.clickLogin();
        logger.info("Clicked on Login ");
        
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
    	lp=new LoginPage(driver);
         
    	lp.setEmail(email);
        logger.info("Provided Email ");
        lp.setPassword(pwd);
        logger.info("Provided Password ");
        
    }

    @When("Click on Login button")
    public void click_on_login_button() {
        lp.clickLogin();
        logger.info("Clicked on Login button");
        
    }


    @Then("^User navigates to MyAccount Page$")
    public void user_navigates_to_my_account_page() {
    	macc=new MyAccountPage(driver);
    	
		boolean targetpage=macc.isMyAccountPageExists();
	
        if(targetpage)
        {
            logger.info("Login Success ");
            Assert.assertTrue(true);
        }
        else
        {
            logger.error("Login Failed ");
            Assert.assertTrue(false);
        }

    }

    //*******   Data Driven test method    **************
    @Then("check User navigates to MyAccount Page by passing Email and Password with excel row {string}")
    public void check_user_danavigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows)
    {
        datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        lp=new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);

        lp.clickLogin();
        try
        {
            boolean targetpage=macc.isMyAccountPageExists();

            if(exp_res.equals("Valid"))
            {
                if(targetpage==true)
                {
                    MyAccountPage myaccpage=new MyAccountPage(driver);
                    myaccpage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.equals("Invalid"))
            {
                if(targetpage==true)
                {
                    macc.clickLogout();
                    Assert.assertTrue(false);
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }


        }
        catch(Exception e)
        {

            Assert.assertTrue(false);
        }
        driver.close();
    }
    
    
    

    //*******   Account Registration Methods    **************
//    package stepDefinitions;
//
//    import java.util.Iterator;
//
//    import java.util.Set;
//
//    import org.openqa.selenium.By;
//    import org.openqa.selenium.WebDriver;
//    import org.openqa.selenium.chrome.ChromeDriver;
//    import org.testng.Assert;
//
//    import io.cucumber.java.en.Given;
//    import io.cucumber.java.en.Then;
//    import io.cucumber.java.en.When;
//    import pageObjects.CheckoutPage;
//    import pageObjects.LandingPage;
//    import utils.TestContextSetup;
//
//    public class CheckoutPageStepDefinition {
//    public WebDriver driver;
//    public String landingPageProductName;
//    public String offerPageProductName;
  
//    TestContextSetup testContextSetup;
//    //Spring framework, EJB, 
//    //SRP
//    //
//    public CheckoutPageStepDefinition(TestContextSetup testContextSetup)
//    {
//    	this.testContextSetup=testContextSetup;
//    	this.checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
//    }
//

    @Then("verify user has ability to enter promo code and place the order")
    public void  verify_user_has_ability_enter_promo()
    {
    	
    	Assert.assertTrue(checkoutPage.VerifyPromoBtn());
    	Assert.assertTrue(checkoutPage.VerifyPlaceOrder());
    	
    	
    }

    @Then("user verify the mobile cost {string}")
    public void user_verify_the(String name) throws InterruptedException
    {
    	 this.checkoutPage = new CheckoutPage(driver);
    	 System.out.println(name);
    	
    	String tst= checkoutPage.getCost();
    	System.out.println("mmmmmmmmm"+tst);
    	Assert.assertEquals(tst, name);
    	Actions act = new Actions(driver);
       
//    	act.keyDown(Keys.CONTROL)
//    	   .click(element)
//    	   .keyUp(Keys.CONTROL)
//    	   .build()
//    	   .perform();
//    	act.clickAndHold().moveToElement(null).release().build().perform();
//    	Select sel = new Select(null);
//    	List<WebElement> ele = sel.getOptions();
//    	
//    	for(WebElement eled : ele) {
//    		
//    	if (eled.getText().equals("")) {
//    		
//    		sel.selectByVisibleText(eled.getText());
//    	}
//    		
//    	}
//    	Set<String> windows1=  driver.getWindowHandles();
//    	String  originalwindow = driver.getWindowHandle();
//    for(String win :windows1 ) {
//    	
//if(!win.equals(originalwindow)) {
//	
//	driver.switchTo().window(win);
//	driver.close();
//	
    	
    	//Assertion to extract name from screen and compare with name
    }

    @Then("^User proceeds to Checkout and validate the (.+) items in checkout page$")
    public void user_proceeds_to_checkout(String name) throws InterruptedException
    {
    	
    	checkoutPage.CheckoutItems();
    	//Assertion to extract name from screen and compare with name
    	
  
    }
    @Then("user verify the duplicate list")
    public void verify_duplicate_List(String url) {
     
    }
    @Then("user mouse hover")
    public void verify_user_mouseHover() throws InterruptedException {
     WebElement ele = driver.findElement(By.xpath("//*[text()='Electronics']"));
     WebElement ele1 = driver.findElement(By.xpath("//*[text()='Men']"));	
     Actions act = new Actions(driver);
     act.moveToElement(ele).build().perform();
     act.contextClick(ele).build().perform();
     act.sendKeys(Keys.ENTER);
     Thread.sleep(3000);
     
    }
    
//    @Then("user perform action")
//    public void verify_user_perform_action() throws InterruptedException {
//     WebElement ele = driver.findElement(By.xpath("//button[@id='tabButton']"));
//     JavascriptExecutor jse =  (JavascriptExecutor)driver;
//     jse.executeScript(br, null"document.getElementById("tabButton").click();
     
    @Then("user perform action")
     public void verify_user_perform_action() throws InterruptedException {
    
      WebElement ele = driver.findElement(By.xpath("//button[@id='tabButton']"));
      JavascriptExecutor jse =  (JavascriptExecutor)driver;
      jse.executeScript("document.getElementById('tabButton')).click()");
       
     
    }
    

    @Then("user perform alert")
     public void verify_user_perform_alert() throws InterruptedException {
    	
//      WebElement ele = driver.findElement(By.xpath("//button[@id='tabButton']"));
      driver.manage().window().maximize();
      
      WebDriverWait wa = new WebDriverWait(driver,30);
      WebElement button = wa.until(ExpectedConditions.presenceOfElementLocated((By.id("confirmButton"))));
      button.click();
      
      
//      JavascriptExecutor jse =  (JavascriptExecutor)driver;
//      jse.executeScript("document.getElementById('confirmButton').click()");
//       Thread.sleep(3000);
       
       Alert alt = driver.switchTo().alert();
   	   logger.info("Switched to alert");
   	
       String text1 = alt.getText();
       
   	logger.info("Got the text" +text1 );
//    String text =  driver.switchTo().alert().getText(); 
    String expectedtext = "Do you confirm action?";
    Assert.assertEquals(expectedtext, text1);
    logger.info("text verified");
    if(text1.equals(expectedtext)) {
    	
    alt.accept();
    }
    
   
//    driver.switchTo().alert().dismiss();
     
    Thread.sleep(3000);
    
    
    
    }
    @Then("Then user perform drag and drop")
    public void verify_drag_drop() {
    List<WebElement> li  = driver.findElements(By.tagName("iframe"));	
    for(int i =0;i<li.size();i++) {
    driver.switchTo().frame(i);
  
    WebElement draggable = driver.findElement(By.id("draggable"));
    WebElement droppable = driver.findElement(By.id("droppable"));
    
    if(draggable.isDisplayed()) {
    	
    Actions act = new Actions(driver);
    act.dragAndDrop(draggable, droppable).build().perform();
    
    }
    }
    	
    }
//    @Given("opens URL {string}")
//    public void open_url(String url) {
//        driver.get(url);
//        driver.manage().window().maximize();
//    }
    	
    
   

}
