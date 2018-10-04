package seleniumgluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumWebDriver {

	public enum Browsers{Chrome, PhantomJS};
	
	private WebDriver driver;
	
	public SeleniumWebDriver(Browsers browser) throws Exception 
	{
		switch ( browser ) {		
			case Chrome:	
			{ 
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(); 
			break; 
			}
			case PhantomJS:	
			{ 
				DesiredCapabilities caps = new DesiredCapabilities();
	            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"C:\\Drivers\\phantomjs.exe");
	            driver = new PhantomJSDriver(caps);
	            break;	
			}
		
			default:		
			{ 
			throw new Exception();	
			}
		}
		
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
}
