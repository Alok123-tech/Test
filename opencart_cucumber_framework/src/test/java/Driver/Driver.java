package Driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import com.google.common.base.Objects;

public class Driver {

	private static  WebDriver driver = null;
	private static  ThreadLocal<WebDriver> dr = new ThreadLocal<>();
	
	
	public static WebDriver getDriver() {
		return dr.get();
		
		
	}
	

	public static WebDriver setDriver(WebDriver driver) {
		return dr.get();
		
		
	}
	
	public static void unload() {
		
		dr.remove();
	}
	
	
	public static void initDriver() {
		if(Objects.isNull(driver)) {
			System.setProperty("webdriver.chrome.driver", "");
			driver =  new ChromeDriver();
			setDriver(driver);
			getDriver().get(" ");
			
		}}
		

		public static void quitDriver() {
			if(Objects.nonNull(getDriver())) {
			getDriver().quit();
				unload();
			}
		
	}
	
}
