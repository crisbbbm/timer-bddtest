package seleniumgluecode;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import seleniumgluecode.SeleniumWebDriver.Browsers;

public class StepDefiniton {

	private WebDriver driver;	
	private SeleniumWebDriver selenium;
	
	private int timer;
	private String userInput;
	private String actualText;
	private int loopsCountToVerify;
	private static final String TIMEOUT_TEXT = "Time Expired!";
	private static final String URL = "https://e.ggtimer.com";
	
	@Before
	public void BeforeSteps() throws Exception {
		selenium = new SeleniumWebDriver(Browsers.Chrome);	
		driver = selenium.getDriver();
	}
	
	@After public void AfterSteps(){ 
		driver.quit(); 
	   }
	
	@Given("^user is on timer page$")
	public void user_is_on_timer_page() throws Throwable {
		
        driver.get(URL);

	}

	@When("^user enters (.*?) in timer$")
	public void user_enters_seconds_in_timer(String input) throws Throwable {
		userInput = input.trim();
		driver.findElement(By.id("start_a_timer")).clear();
		driver.findElement(By.id("start_a_timer")).sendKeys(input);
	}

	@When("^user clicks the go button$")
	public void user_clicks_the_go_button() throws Throwable {
		driver.findElement(By.id("timergo")).click();
	}
	
	@When("^user leaves the timer running for maximum (\\d+) seconds$")
	public void user_leaves_the_timer_running_for_maximum_seconds(int loopsCountToVerify) throws Throwable {
		this.loopsCountToVerify = loopsCountToVerify;
	}

	
	@Then("^the timer (.*?) and it decreses by (\\d+) seconds$")
	public void the_timer__and_it_decreses_by_seconds(String status, int countdownSeconds) throws Throwable {
		
		if (status.equals("fails")) {
			
			verifyTimeout();		
		
		} else if (status.equals("works")) {
			
			String actualText="";	
			String expectedText = "";
					
			timer = getInputSeconds(userInput);
			if(timer == 0)
				Assert.fail("User input type valid, but not handled yet in tests!");
			else if (timer == 1) 			
				verifyTimeout();
			else {
				Assert.assertFalse("Alert dialog present event if user input valid! User input: " + userInput, isAlertPresent() );			
							
				//Due to page loading latency in the WebDriver, count down starts from user input - 2
				timer = timer - 2;
				int loopsCount = 0;	
				for (int count = timer; count >= -1; count-=countdownSeconds ) {
					loopsCount++;
					actualText = driver.findElement(By.id("progressText")).getText();
					if(count == 1) {
						Assert.assertEquals("1 second", actualText); 					
					} else if(count == 0) {
						verifyTimeout();
						break;
					} else {
						expectedText = getExpectedText(count);
						Assert.assertEquals("Count down not decreased by one seond. Expected: '"+ expectedText +"' Actual:'" + actualText + "'", expectedText, actualText);			
					}
					TimeUnit.SECONDS.sleep(countdownSeconds);
					if (loopsCount ==  loopsCountToVerify)
						break;
				}
			}
		
		} else {
			Assert.fail("Invalid expected result in test data. Should be 'works' or 'fails'");
		}
	}
	
	//Helper classes
	
	/**
     * Returns the expected count down text to be verified from the total number of seconds 
    */
	public String getExpectedText(int inputSeconds) {
		String expectedText = "";
		
		String[] stringArray = LocalTime.MIN.plusSeconds(inputSeconds).toString().split(":");
		String hours = stringArray[0].replaceFirst("^0+(?!$)", "");
		String minutes = stringArray[1].replaceFirst("^0+(?!$)", "");
		String seconds = stringArray[2].replaceFirst("^0+(?!$)", "");	
		
		if(!hours.equals("0"))
			if (hours.equals("1"))
				expectedText = expectedText + hours + " hour ";
			else
				expectedText = expectedText + hours + " hours ";
		if(!minutes.equals("0"))
			if (minutes.equals("1"))
				expectedText = expectedText + minutes + " minute ";
			else
				expectedText = expectedText + minutes + " minutes ";
		if(!seconds.equals("0"))
			if (seconds.equals("1"))
				expectedText = expectedText + seconds + " second";
			else
				expectedText = expectedText + seconds + " seconds";
		 
		return expectedText;
			
	}
	
	/**
     * Returns if an alert dialog is present or not
    */
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   
	    catch (NoAlertPresentException Ex) { 
	        return false; 
	        
	    } catch   (TimeoutException Ex)  {
	    	return false; 
	    }
	    
	}
	
	
  /**
   * Returns the total number of seconds introduced in the timer
   */
	public int getInputSeconds(String userInput) {
		String regex = "^[0-9]*$";
		if(userInput.matches(regex))
			return Integer.parseInt(userInput);
		else
			if(userInput.contains("seconds"))
				return Integer.parseInt(userInput.replaceAll("\\D+",""));
			else if(userInput.contains("minute"))
				return Integer.parseInt(userInput.replaceAll("\\D+","")) * 60;
			else if(userInput.contains("hours"))
				return Integer.parseInt(userInput.replaceAll("\\D+","")) * 60 * 60;
			else return 0;
	}
	
	/**
	   * Verifies the alert dialog and page when Timer finished
	   */
	public void verifyTimeout() throws InterruptedException {
		
		//Possible bug
		TimeUnit.SECONDS.sleep(2);
		
		Assert.assertTrue("Alert dialog not present!", isAlertPresent());
		Assert.assertEquals("Final Timeout text not present in allert dialog!",TIMEOUT_TEXT,  driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		Assert.assertFalse("Alert dialog appeard a second time!", isAlertPresent());
		actualText = driver.findElement(By.id("progressText")).getText();		
		Assert.assertEquals("Final Timeout text not present!", TIMEOUT_TEXT, actualText);
		
	}
	
}
